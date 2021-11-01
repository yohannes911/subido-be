package org.subido.core.dto;

import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.Specification;
import org.subido.model.entity.TodoItem;

import javax.annotation.Nonnull;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class TodoSpecification implements Specification<TodoItem> {

    public static final long serialVersionUID = 1;

    private final QueryRequestDto.FieldFilterDto fieldFilter;

    public TodoSpecification(QueryRequestDto.FieldFilterDto fieldFilter) {
        this.fieldFilter = fieldFilter;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public Predicate toPredicate(final @Nonnull Root<TodoItem> root, final CriteriaQuery<?> query, final @Nonnull CriteriaBuilder criteriaBuilder) {
        Path path = root.get(fieldFilter.getFieldName().name());
        Class<?> javaType = root.getJavaType();
        query.distinct(true);
        String value = fieldFilter.getFilterValue();
        // IN, NOT_IN, LIKE, NOT_LIKE, LT, LE, GT, GE, EQ, NOT_EQ
        switch (fieldFilter.getOperation()) {
            case IN:
                return createInClause(root);
            case NOT_IN:
                return createInClause(root)
                        .not();
            case LIKE:
                return criteriaBuilder.like(criteriaBuilder.lower(path.as(String.class)), "%" + value.toLowerCase() + "%");
            case NOT_LIKE:
                return criteriaBuilder.notLike(criteriaBuilder.lower(path.as(String.class)), "%" + value.toLowerCase() + "%");
            case LT:
                return criteriaBuilder.lessThan(path, createTypeSafeField(javaType, value));
            case LE:
                return criteriaBuilder.lessThanOrEqualTo(path, createTypeSafeField(javaType, value));
            case GT:
                return criteriaBuilder.greaterThan(path, createTypeSafeField(javaType, value));
            case GE:
                return criteriaBuilder.greaterThanOrEqualTo(path, createTypeSafeField(javaType, value));
            case EQ:
                return criteriaBuilder.equal(path, createTypeSafeField(javaType, value));
            case NOT_EQ:
                return criteriaBuilder.notEqual(path, createTypeSafeField(javaType, value));
            case IS_NULL:
                return criteriaBuilder.isNull(path);
            case IS_NOT_NULL:
                return criteriaBuilder.isNotNull(path);
            default:
                throw new IllegalArgumentException("No such filter operation: " + fieldFilter.getOperation());
        }
    }

    protected Predicate createInClause(Root<TodoItem> root) {
        String[] elements = fieldFilter.getFilterValue().split(",");
        Join<Object, Object> join = root.join(fieldFilter.getFieldName().name());
        return join.in(Arrays.stream(elements)
                .map(s -> createTypeSafeField(join.getJavaType(), s))
                .collect(Collectors.toSet()));
    }

    @SuppressWarnings("rawtypes")
    protected Comparable createTypeSafeField(Class<?> fieldType, String fieldValue) {
        Comparable<?> result = null;
        try {
            if (fieldType.equals(ZonedDateTime.class)) {
                result = ZonedDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(fieldValue)), ZoneOffset.systemDefault());
            } else if (fieldType.equals(Long.class)) {
                result = Long.parseLong(fieldValue);
            } else if (fieldType.equals(String.class) || fieldType.isEnum()) {
                result = fieldValue;
            }
        } catch (NumberFormatException | DateTimeParseException | IndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("Cannot convert field value: " + fieldValue, ex);
        }
        return result;
    }
}
