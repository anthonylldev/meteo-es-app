package com.anthonylldev.meteoesapi.forecast.application.dto;

import java.util.List;

public record ForecastDto(
        double averageTemperature,
        String temperatureUnit,
        List<PrecipitationProbabilityDto> precipitationProbability,
        String date
) {
}
