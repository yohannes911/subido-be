package org.subido.core.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ToDoItemDto extends UpdateToDoItemDto {

    private ZonedDateTime createdAt;
}
