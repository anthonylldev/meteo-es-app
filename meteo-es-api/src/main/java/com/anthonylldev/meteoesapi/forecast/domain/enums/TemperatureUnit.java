package com.anthonylldev.meteoesapi.forecast.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TemperatureUnit {
    CELSIUS("G_CEL"),
    FAHRENHEIT("G_FAH");

    private final String code;

    public static TemperatureUnit fromCode(String code) {
        for (TemperatureUnit unit : values()) {
            if (unit.code.equalsIgnoreCase(code)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Invalid temperature unit: " + code);
    }
}
