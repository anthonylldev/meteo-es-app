package com.anthonylldev.meteoesapi.forecast.application.port.out;

import com.anthonylldev.meteoesapi.forecast.domain.model.Forecast;

public interface ForecastGateway {
    Forecast getForecast(String municipalityCode);
}
