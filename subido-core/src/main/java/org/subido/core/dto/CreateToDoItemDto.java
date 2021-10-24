package org.subido.core.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.subido.model.Priority;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
public class CreateToDoItemDto {

    @NotEmpty
    @Length(min = 1, max = 128)
    private String name;

    private ZonedDateTime deadline;

    @NotNull
    private Priority priority;
}
