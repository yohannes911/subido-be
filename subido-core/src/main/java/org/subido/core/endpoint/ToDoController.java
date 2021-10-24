package org.subido.core.endpoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.subido.core.dto.CreateToDoItemDto;
import org.subido.core.dto.ListRequestDto;
import org.subido.core.dto.ToDoItemDto;
import org.subido.core.dto.UpdateToDoItemDto;
import org.subido.core.service.ToDoItemService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

@RequiredArgsConstructor
@Tag(name = "ToDo")
@RestController
@RequestMapping("todo")
public class ToDoController {

    private final ToDoItemService toDoItemService;

    @Operation(summary = "Select all ToDo item")
    @PostMapping(path = "list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Flux<ToDoItemDto> list(final @RequestBody @Validated ListRequestDto request) {
        return Mono.just(request)
                .flatMapMany(req -> toDoItemService.list(req.getPageable()));
    }

    @Operation(summary = "Delete ToDo item with provided id")
    @PostMapping(path = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ToDoItemDto> create(final @RequestBody @Validated CreateToDoItemDto dto) {
        return Mono.just(dto)
                .flatMap(toDoItemService::create);
    }

    @Operation(summary = "Updates ToDo item with provided id and dto")
    @PutMapping(path = "{itemId}/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ToDoItemDto> update(final @PathVariable("itemId") Long itemId, final @RequestBody @Validated UpdateToDoItemDto dto) {
        return Mono.just(Tuples.of(itemId, dto))
                .flatMap(t2 -> toDoItemService.update(itemId, dto));
    }

    @Operation(summary = "Delete ToDo item with provided id")
    @DeleteMapping(path = "{itemId}/delete")
    public Mono<Void> delete(final @PathVariable("itemId") Long itemId) {
        return Mono.just(itemId)
                .flatMap(toDoItemService::delete);
    }
}
