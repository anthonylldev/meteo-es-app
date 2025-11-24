package com.anthonylldev.meteoesapi.forecast.infrastructure.adapter.out.aemet.dto;

import java.util.List;

public record AemetForecastDto(
        List<AemetDayDto> dia
) {
}
