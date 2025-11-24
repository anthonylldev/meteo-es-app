package com.anthonylldev.meteoesapi.infrastructure.aemet.dto;

public record AemetMetadataDto(
        String descripcion,
        String estado,
        String datos,
        String metadatos
) {
}
