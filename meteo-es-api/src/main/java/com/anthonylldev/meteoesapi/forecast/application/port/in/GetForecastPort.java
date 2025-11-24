package com.anthonylldev.meteoesapi.forecast.application.port.in;

import com.anthonylldev.meteoesapi.forecast.application.dto.ForecastDto;
import com.anthonylldev.meteoesapi.forecast.domain.enums.TemperatureUnit;

public interface GetForecastPort {
    ForecastDto getForecast(String municipalityCode, TemperatureUnit temperatureUnit);
}
