package com.anthonylldev.meteoesapi.municipality.infrastructure.adapter.out.aemet.exception;

public class InvalidMunicipalityIdException extends RuntimeException {
    public InvalidMunicipalityIdException(String id) {
        super("Invalid municipality ID: " + id);
    }
}
