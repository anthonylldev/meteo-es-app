package com.anthonylldev.meteoesapi.forecast.infrastructure.adapter.out.aemet.dto;

public record AemetSkyStateDto(
        String value,
        String periodo,
        String descripcion
) {
}
