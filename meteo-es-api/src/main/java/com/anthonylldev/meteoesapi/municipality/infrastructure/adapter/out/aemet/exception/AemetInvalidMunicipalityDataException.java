package com.anthonylldev.meteoesapi.municipality.infrastructure.adapter.out.aemet.exception;

public class AemetInvalidMunicipalityDataException extends RuntimeException {
    public AemetInvalidMunicipalityDataException() {
        super("AEMET returned invalid municipality data.");
    }
}
