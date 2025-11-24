package com.anthonylldev.meteoesapi.infrastructure.aemet.exception;

public class AemetRateLimitExceededException extends RuntimeException {
    public AemetRateLimitExceededException() {
        super("AEMET rate limit exceeded. Try again later.");
    }
}
