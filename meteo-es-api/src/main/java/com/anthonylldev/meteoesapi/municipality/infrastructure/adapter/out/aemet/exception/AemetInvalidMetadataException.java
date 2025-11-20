package com.anthonylldev.meteoesapi.municipality.infrastructure.adapter.out.aemet.exception;

public class AemetInvalidMetadataException extends RuntimeException {
    public AemetInvalidMetadataException() {
        super("AEMET returned invalid metadata or missing data URL.");
    }
}
