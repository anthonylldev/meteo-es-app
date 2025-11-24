package com.anthonylldev.meteoesapi.forecast.infrastructure.adapter.out.aemet.dto;

import java.util.List;

public record AemetDayDto(
        List<AemetSkyStateDto> estadoCielo,
        List<AemetPeriodValueDto> precipitacion,
        List<AemetPeriodValueDto> probPrecipitacion,
        List<AemetPeriodValueDto> temperatura,
        String fecha
) {
}
