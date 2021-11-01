package org.subido.core.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Getter
@Setter
public class QueryRequestDto {

    private final Integer pageSize;
    private final Integer pageNumber;
    private List<SortDto> sorts;
    private List<FieldFilterDto> fieldFilters;

    @JsonCreator
    public QueryRequestDto(@JsonProperty("pageSize") int pageSize,
                           @JsonProperty("pageNumber") int pageNumber,
                           @JsonProperty("sorts") List<SortDto> sorts,
                           @JsonProperty("fieldFilters") List<FieldFilterDto> fieldFilters) {
        this.pageSize = Objects.requireNonNullElse(pageSize, 5);
        this.pageNumber = Objects.requireNonNullElse(pageNumber, 0);
        this.sorts = Objects.requireNonNullElse(sorts, List.of());
        this.fieldFilters = Objects.requireNonNullElse(fieldFilters, List.of());
    }

    public void addFilter(FieldFilterDto filter) {
        requireNonNull(filter);
        fieldFilters.add(filter);
    }

    @JsonIgnore
    public Pageable getPageable() {
        List<Sort.Order> orders = sorts.stream()
                .map(SortDto::toOrder)
                .collect(Collectors.toList());
        return PageRequest.of(this.pageNumber, this.pageSize, Sort.by(orders));
    }

    @Schema(enumAsRef = true)
    public enum FieldName {
        createdAt, name, deadline, priority
    }

    @Schema(enumAsRef = true)
    public enum FilterOperation {
        IN, NOT_IN, LIKE, NOT_LIKE, LT, LE, GT, GE, EQ, NOT_EQ, IS_NULL, IS_NOT_NULL
    }

    @Getter
    @EqualsAndHashCode
    @Valid
    public static class FieldFilterDto {

        @NotNull
        private final FieldName fieldName;

        @NotNull
        private final FilterOperation operation;

        @NotNull
        private final String filterValue;

        @JsonCreator
        public FieldFilterDto(@JsonProperty("fieldName") @NotNull @NonNull FieldName fieldName,
                              @JsonProperty("operation") @NotNull @NonNull FilterOperation operation,
                              @JsonProperty("filterValue") @NotNull @NonNull String filterValue) {
            this.fieldName = fieldName;
            this.operation = operation;
            this.filterValue = filterValue;
        }
    }

    @Schema(enumAsRef = true)
    public enum SortDirection {
        ASC, DESC
    }

    @Getter
    @EqualsAndHashCode
    public static class SortDto {

        @NotNull
        private final String fieldName;

        @NotNull
        @Schema(enumAsRef = true)
        private final SortDirection direction;

        @JsonCreator
        public SortDto(@JsonProperty("fieldName") @NotNull @NonNull String fieldName,
                       @JsonProperty("direction") @NotNull @NonNull SortDirection direction) {
            this.fieldName = fieldName;
            this.direction = direction;
        }

        private Sort.Order toOrder() {
            return new Sort.Order(Sort.Direction.fromString(direction.name()), fieldName);
        }
    }
}
