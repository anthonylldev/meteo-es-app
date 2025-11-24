package com.anthonylldev.meteoesapi.forecast.infrastructure.adapter.out.aemet;

import com.anthonylldev.meteoesapi.forecast.application.port.out.ForecastGateway;
import com.anthonylldev.meteoesapi.forecast.domain.model.Forecast;
import com.anthonylldev.meteoesapi.forecast.infrastructure.adapter.out.aemet.dto.AemetWeatherDto;
import com.anthonylldev.meteoesapi.forecast.infrastructure.adapter.out.aemet.mapper.AemetForecastMapper;
import com.anthonylldev.meteoesapi.infrastructure.aemet.AemetWebClient;
import com.anthonylldev.meteoesapi.infrastructure.aemet.dto.AemetMetadataDto;
import com.anthonylldev.meteoesapi.infrastructure.aemet.exception.AemetInvalidDataException;
import com.anthonylldev.meteoesapi.infrastructure.aemet.exception.AemetInvalidMetadataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AemetForecastAdapter implements ForecastGateway {

    private final AemetWebClient aemetWebClient;
    private final AemetForecastMapper mapper;

    public AemetForecastAdapter(AemetWebClient aemetWebClient, AemetForecastMapper mapper) {
        this.aemetWebClient = aemetWebClient;
        this.mapper = mapper;
    }

    @Override
    public Forecast getForecast(String municipalityCode) {
        log.info("Requesting forecast data from AEMET...");

        AemetMetadataDto metadata = aemetWebClient.getForecastMetadata(municipalityCode);

        if (metadata == null || metadata.datos() == null) {
            throw new AemetInvalidMetadataException();
        }

        log.info("Fetching forecast data from: {}", metadata.datos());

        AemetWeatherDto aemetWeatherDto = aemetWebClient.getWeather(metadata.datos());

        if (aemetWeatherDto == null) {
            throw new AemetInvalidDataException();
        }

        log.info("Successfully retrieved forecast data.");

        return mapper.toForecast(aemetWeatherDto);
    }
}
