package com.anthonylldev.meteoesapi.forecast.infrastructure.adapter.in.rest;

import com.anthonylldev.meteoesapi.forecast.application.dto.ForecastDto;
import com.anthonylldev.meteoesapi.forecast.application.port.in.GetForecastPort;
import com.anthonylldev.meteoesapi.forecast.domain.enums.TemperatureUnit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/forecasts")
public class ForecastController {

    private final GetForecastPort forecastPort;

    public ForecastController(GetForecastPort forecastPort) {
        this.forecastPort = forecastPort;
    }

    @GetMapping
    public ResponseEntity<ForecastDto> getForecast(
            @RequestParam @NotBlank String municipalityCode,
            @RequestParam @NotNull String temperatureUnit
    ) {
        return ResponseEntity.ok(forecastPort.getForecast(municipalityCode, TemperatureUnit.fromCode(temperatureUnit)));
    }
}
