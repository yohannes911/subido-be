package org.subido.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Schema
public class TodoItemDto extends UpdateTodoItemDto {

    private ZonedDateTime createdAt;
}
