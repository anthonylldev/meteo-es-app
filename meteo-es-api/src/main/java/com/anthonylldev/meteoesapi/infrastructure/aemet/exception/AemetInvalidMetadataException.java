package com.anthonylldev.meteoesapi.infrastructure.aemet.exception;

public class AemetInvalidMetadataException extends RuntimeException {
    public AemetInvalidMetadataException() {
        super("AEMET returned invalid metadata or missing data URL.");
    }
}
