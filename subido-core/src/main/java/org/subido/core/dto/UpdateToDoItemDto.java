package org.subido.core.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class UpdateTodoItemDto extends CreateTodoItemDto {

    @NotNull
    private Long id;
}
