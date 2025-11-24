package com.anthonylldev.meteoesapi.infrastructure.aemet.exception;

public class AemetInvalidDataException extends RuntimeException {

    public AemetInvalidDataException() {
        super("AEMET returned invalid data.");
    }
}
