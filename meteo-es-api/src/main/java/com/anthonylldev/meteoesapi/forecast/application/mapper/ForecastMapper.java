package com.anthonylldev.meteoesapi.forecast.application.mapper;

import com.anthonylldev.meteoesapi.forecast.application.dto.ForecastDto;
import com.anthonylldev.meteoesapi.forecast.application.dto.PrecipitationProbabilityDto;
import com.anthonylldev.meteoesapi.forecast.domain.enums.TemperatureUnit;
import com.anthonylldev.meteoesapi.forecast.domain.model.Forecast;
import com.anthonylldev.meteoesapi.forecast.domain.model.PrecipitationProbability;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ForecastMapper {
    public ForecastDto toDto(Forecast forecast, TemperatureUnit temperatureUnit) {
        double averageTemperature = forecast.averageTemperature();

        if (temperatureUnit == TemperatureUnit.FAHRENHEIT) {
            averageTemperature = (averageTemperature * 9 / 5) + 32;
        }

        return new ForecastDto(averageTemperature, temperatureUnit.getCode(), groupIntoFourRanges(forecast.precipitationProbability()), forecast.date());
    }

    public List<PrecipitationProbabilityDto> groupIntoFourRanges(List<PrecipitationProbability> hourly) {
        return List.of(
                buildRange(hourly, 0, 6),
                buildRange(hourly, 6, 12),
                buildRange(hourly, 12, 18),
                buildRange(hourly, 18, 24)
        );
    }

    private PrecipitationProbabilityDto buildRange(List<PrecipitationProbability> list, int start, int end) {

        var filtered = list.stream()
                .filter(p -> {
                    int hour = Integer.parseInt(p.period());
                    return hour >= start && hour < end;
                })
                .toList();

        double avgProbability = filtered.stream()
                .mapToDouble(PrecipitationProbability::probability)
                .average()
                .orElse(0);

        String periodLabel = "%02d-%02d".formatted(start, end);

        return new PrecipitationProbabilityDto(avgProbability, periodLabel);
    }

}
