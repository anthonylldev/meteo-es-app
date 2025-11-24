package com.anthonylldev.meteoesapi.forecast.domain.model;

public record PrecipitationProbability(
        double probability,
        String period
) {
}
