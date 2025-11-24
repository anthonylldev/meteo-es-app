package com.anthonylldev.meteoesapi.forecast.application.usecase;

import com.anthonylldev.meteoesapi.forecast.application.dto.ForecastDto;
import com.anthonylldev.meteoesapi.forecast.application.mapper.ForecastMapper;
import com.anthonylldev.meteoesapi.forecast.application.port.in.GetForecastPort;
import com.anthonylldev.meteoesapi.forecast.application.port.out.ForecastGateway;
import com.anthonylldev.meteoesapi.forecast.domain.enums.TemperatureUnit;
import org.springframework.stereotype.Service;

@Service
public class GetForecastUseCase implements GetForecastPort {

    private final ForecastGateway forecastGateway;
    private final ForecastMapper mapper;

    public GetForecastUseCase(ForecastGateway forecastGateway, ForecastMapper mapper) {
        this.forecastGateway = forecastGateway;
        this.mapper = mapper;
    }

    @Override
    public ForecastDto getForecast(String municipalityCode, TemperatureUnit temperatureUnit) {
        return mapper.toDto(forecastGateway.getForecast(municipalityCode), temperatureUnit);
    }
}
