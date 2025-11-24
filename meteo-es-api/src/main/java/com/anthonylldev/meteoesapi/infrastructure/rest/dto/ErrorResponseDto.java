package com.anthonylldev.meteoesapi.infrastructure.rest.dto;

public record ErrorResponseDto(
        String error,
        String message
) {
}
