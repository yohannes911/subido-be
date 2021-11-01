package org.subido.core.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Valid
public class QueryResponseDto {

    @NotNull
    private List<TodoItemDto> todoItems;

    @NotNull
    private Integer pageNumber;

    @NotNull
    private Integer pageSize;

    @NotNull
    private Integer totalPages;

    @NotNull
    private Long totalElements;

    public QueryResponseDto(Page<TodoItemDto> page) {
        this.todoItems = page.getContent();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }
}
