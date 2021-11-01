package org.subido.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
@Validated
@Schema
public class TodoItemDto extends UpdateTodoItemDto {

    @NotNull
    private ZonedDateTime createdAt;
}
