package org.subido.core.endpoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.subido.core.dto.CreateTodoItemDto;
import org.subido.core.dto.QueryRequestDto;
import org.subido.core.dto.QueryResponseDto;
import org.subido.core.dto.TodoItemDto;
import org.subido.core.dto.UpdateTodoItemDto;
import org.subido.core.service.TodoItemService;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Todo")
@RestController
@RequestMapping("api/todo")
public class TodoController {

    private final TodoItemService todoItemService;

    @Operation(summary = "Find Todo item by id")
    @GetMapping(path = "{itemId}")
    public Mono<TodoItemDto> findTodoItem(final @PathVariable("itemId") @Validated Long itemId) {
        return Mono.just(itemId)
                .flatMap(req -> todoItemService.findById(itemId));
    }

    @Operation(summary = "Query Todo items with criteria")
    @PostMapping(path = "query", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<QueryResponseDto> queryTodoItems(final @RequestBody @Validated QueryRequestDto request) {
        return Mono.just(request)
                .flatMap(req -> todoItemService.query(req.getFieldFilters(), req.getPageable()));
    }

    @Operation(summary = "Create Todo item")
    @PostMapping(path = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TodoItemDto> createTodoItem(final @RequestBody @Validated CreateTodoItemDto dto) {
        return Mono.just(dto)
                .flatMap(todoItemService::create);
    }

    @Operation(summary = "Updates Todo item with provided id and dto")
    @PutMapping(path = "{itemId}/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TodoItemDto> updateTodoItem(final @PathVariable("itemId") Long itemId, final @RequestBody @Validated UpdateTodoItemDto dto) {
        return Mono.just(Tuples.of(itemId, dto))
                .flatMap(t2 -> todoItemService.update(itemId, dto));
    }

    @Operation(summary = "Delete Todo item with provided id")
    @DeleteMapping(path = "{itemId}/delete")
    public Mono<TodoItemDto> deleteTodoItem(final @PathVariable("itemId") Long itemId) {
        return Mono.just(itemId)
                .flatMap(todoItemService::delete);
    }
}
