package com.anthonylldev.meteoesapi.forecast.application.dto;

public record PrecipitationProbabilityDto(
        double probability,
        String period
) {
}
