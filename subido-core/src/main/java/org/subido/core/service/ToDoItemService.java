package org.subido.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.subido.core.dto.CreateToDoItemDto;
import org.subido.core.dto.ToDoItemDto;
import org.subido.core.dto.UpdateToDoItemDto;
import org.subido.core.repository.ToDoItemRepository;
import org.subido.model.entity.ToDoItem;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.subido.core.util.BuildUtils.build;

@RequiredArgsConstructor
@Service
public class ToDoItemService {

    private final ToDoItemRepository toDoItemRepository;

    @Transactional(readOnly = true)
    public Flux<ToDoItemDto> list(final Pageable pageable) {
        return Flux.fromIterable(toDoItemRepository.findAll(pageable))
                .map(ToDoItemService::toDto);
    }

    @Transactional
    public Mono<ToDoItemDto> create(final CreateToDoItemDto dto) {
        return Mono.fromCallable(() -> ToDoItemService.toDto(toDoItemRepository.save(ToDoItemService.toItem(dto))));
    }

    @Transactional
    public Mono<ToDoItemDto> update(final Long itemId, final UpdateToDoItemDto dto) {
        return Mono.fromCallable(() -> ToDoItemService.toDto(toDoItemRepository.save(ToDoItemService.updateItem(toDoItemRepository.getById(itemId), dto))));
    }

    @Transactional
    public Mono<Void> delete(final Long itemId) {
        return Mono.fromRunnable(() -> toDoItemRepository.delete(toDoItemRepository.getById(itemId)));
    }

    static ToDoItemDto toDto(ToDoItem item) {
        return build(new ToDoItemDto(), dto -> {
            dto.setId(item.getId());
            dto.setName(item.getName());
            dto.setDeadline(item.getDeadline());
            dto.setCreatedAt(item.getCreatedAt());
            dto.setPriority(item.getPriority());
        });
    }

    static ToDoItem toItem(CreateToDoItemDto dto) {
        return updateItem(new ToDoItem(), dto);
    }

    static <I extends CreateToDoItemDto> ToDoItem updateItem(ToDoItem item, I dto) {
        return build(item, i -> {
            i.setName(dto.getName());
            i.setDeadline(dto.getDeadline());
            i.setPriority(dto.getPriority());
        });
    }
}
