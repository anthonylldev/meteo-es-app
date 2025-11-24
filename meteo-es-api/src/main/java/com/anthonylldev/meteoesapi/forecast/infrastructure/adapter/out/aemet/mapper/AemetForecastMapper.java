package com.anthonylldev.meteoesapi.forecast.infrastructure.adapter.out.aemet.mapper;

import com.anthonylldev.meteoesapi.forecast.domain.model.Forecast;
import com.anthonylldev.meteoesapi.forecast.domain.model.PrecipitationProbability;
import com.anthonylldev.meteoesapi.forecast.infrastructure.adapter.out.aemet.dto.AemetDayDto;
import com.anthonylldev.meteoesapi.forecast.infrastructure.adapter.out.aemet.dto.AemetPeriodValueDto;
import com.anthonylldev.meteoesapi.forecast.infrastructure.adapter.out.aemet.dto.AemetWeatherDto;
import com.anthonylldev.meteoesapi.infrastructure.aemet.exception.AemetInvalidDataException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class AemetForecastMapper {

    public Forecast toForecast(AemetWeatherDto dto) {
        var today = dto.prediccion().dia().stream().filter(this::isToday).findFirst().orElseThrow(AemetInvalidDataException::new);
        var avgTemp = today.temperatura().stream().map(aemetPeriodValueDto -> toDouble(aemetPeriodValueDto.value())).reduce(Double::sum).orElse(0.0) / today.temperatura().size();
        return new Forecast(avgTemp, today.precipitacion().stream().map(this::toDto).toList(), today.fecha());
    }

    public boolean isToday(AemetDayDto day) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        LocalDate fromDto = LocalDateTime.parse(day.fecha(), formatter).toLocalDate();
        LocalDate today = LocalDate.now();

        return fromDto.equals(today);
    }

    public PrecipitationProbability toDto(AemetPeriodValueDto aemetPeriodValueDto) {
        return new PrecipitationProbability(toDouble(aemetPeriodValueDto.value()), aemetPeriodValueDto.periodo());
    }

    public double toDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
