package org.subido.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.subido.core.dto.CreateTodoItemDto;
import org.subido.core.dto.QueryRequestDto;
import org.subido.core.dto.QueryResponseDto;
import org.subido.core.dto.TodoItemDto;
import org.subido.core.dto.TodoSpecification;
import org.subido.core.dto.UpdateTodoItemDto;
import org.subido.core.repository.TodoItemRepository;
import org.subido.model.entity.TodoItem;
import reactor.core.publisher.Mono;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.subido.core.util.BuildUtils.build;

@RequiredArgsConstructor
@Service
public class TodoItemService {

    private final TodoItemRepository toDoItemRepository;

    @Transactional(readOnly = true)
    public Mono<TodoItemDto> findById(final Long id) {
        TodoItemDto todoItemDto = toDoItemRepository.findById(id)
                .map(TodoItemService::toDto)
                .orElseThrow(() -> new EntityNotFoundException("no todoItem found for ID: " + id));
        return Mono.just(todoItemDto);
    }

    @Transactional(readOnly = true)
    public Mono<QueryResponseDto> query(List<QueryRequestDto.FieldFilterDto> fieldFilters, final Pageable pageable) {
        Specification<TodoItem> specification = null;
        if (!fieldFilters.isEmpty()) {
            specification = new TodoSpecification(fieldFilters.get(0));
            for (int i = 1; i < fieldFilters.size(); i++) {
                specification = specification.and(new TodoSpecification(fieldFilters.get(i)));
            }
        }
        Page<TodoItem> page = toDoItemRepository.findAll(specification, pageable);
        return Mono.just(page.map(TodoItemService::toDto))
                .map(QueryResponseDto::new);
    }

    @Transactional
    public Mono<TodoItemDto> create(final CreateTodoItemDto dto) {
        TodoItem todoItem = toDoItemRepository.save(TodoItemService.toItem(dto));
        return Mono.just(toDto(todoItem));
    }

    @Transactional
    public Mono<TodoItemDto> update(final Long itemId, final UpdateTodoItemDto dto) {
        TodoItem todoItem = toDoItemRepository.getById(itemId);
        todoItem = toDoItemRepository.save(updateItem(todoItem, dto));
        return Mono.just(toDto(todoItem));
    }

    @Transactional
    public Mono<TodoItemDto> delete(final Long itemId) {
        TodoItem item = toDoItemRepository.getById(itemId);
        toDoItemRepository.delete(item);
        return Mono.just(toDto(item));
    }

    static TodoItemDto toDto(TodoItem item) {
        return build(new TodoItemDto(), dto -> {
            dto.setId(item.getId());
            dto.setName(item.getName());
            dto.setDeadline(item.getDeadline());
            dto.setCreatedAt(item.getCreatedAt());
            dto.setPriority(item.getPriority());
        });
    }

    static TodoItem toItem(CreateTodoItemDto dto) {
        return updateItem(new TodoItem(), dto);
    }

    static <I extends CreateTodoItemDto> TodoItem updateItem(TodoItem item, I dto) {
        return build(item, i -> {
            i.setName(dto.getName());
            i.setDeadline(dto.getDeadline());
            i.setPriority(dto.getPriority());
        });
    }
}
