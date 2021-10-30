package org.subido.core.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class QueryRequestDto {

    private Integer offset;
    private Integer limit;
    private List<Sort> sortList;

    public Pageable getPageable() {
        int offset = Objects.requireNonNullElse(this.offset, 0);
        int limit = Objects.requireNonNullElse(this.limit, 5);
        // TODO: implement
        Sort sort = Sort.unsorted();
        return PageRequest.of(offset * limit, limit + 1, sort);
    }
}
