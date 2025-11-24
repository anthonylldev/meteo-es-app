package com.anthonylldev.meteoesapi.forecast.domain.model;

import java.util.List;

public record Forecast(
        double averageTemperature,
        List<PrecipitationProbability> precipitationProbability,
        String date
) {
}
