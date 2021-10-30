package org.subido.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum Priority {

    LOW,
    MEDIUM,
    HIGH
}
