package org.subido.core.dto;

import lombok.Data;

@Data
public class UpdateTodoItemDto extends CreateTodoItemDto {

    private Long id;
}
