package com.anthonylldev.meteoesapi.infrastructure.rest.handler;

import com.anthonylldev.meteoesapi.infrastructure.aemet.exception.AemetInvalidDataException;
import com.anthonylldev.meteoesapi.infrastructure.aemet.exception.AemetRateLimitExceededException;
import com.anthonylldev.meteoesapi.infrastructure.rest.dto.ErrorResponseDto;
import com.anthonylldev.meteoesapi.infrastructure.aemet.exception.AemetInvalidMetadataException;
import com.anthonylldev.meteoesapi.municipality.infrastructure.adapter.out.aemet.exception.InvalidMunicipalityIdException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.anthonylldev.meteoesapi.infrastructure.rest.enums.ErrorCode.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidMunicipalityIdException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidMunicipalityIdException(InvalidMunicipalityIdException e) {
        log.error("Invalid municipality ID: {}", e.getMessage());
        return ResponseEntity.badRequest().body(
                new ErrorResponseDto(INVALID_MUNICIPALITY_ID.name(), e.getMessage())
        );
    }

    @ExceptionHandler(AemetRateLimitExceededException.class)
    public ResponseEntity<ErrorResponseDto> handleAemetRateLimitExceededException(AemetRateLimitExceededException e) {
        log.error("AEMET rate limit exceeded: {}", e.getMessage());
        return ResponseEntity.status(429).body(
                new ErrorResponseDto(AEMET_RATE_LIMIT_EXCEEDED.name(), e.getMessage())
        );
    }

    @ExceptionHandler(AemetInvalidMetadataException.class)
    public ResponseEntity<ErrorResponseDto> handleAemetInvalidMetadataException(AemetInvalidMetadataException e) {
        log.error("Invalid metadata received from AEMET: {}", e.getMessage());
        return ResponseEntity.badRequest().body(
                new ErrorResponseDto(AEMET_INVALID_METADATA.name(), e.getMessage())
        );
    }

    @ExceptionHandler(AemetInvalidDataException.class)
    public ResponseEntity<ErrorResponseDto> handleAemetInvalidDataException(AemetInvalidDataException e) {
        log.error("Invalid data received from AEMET: {}", e.getMessage());
        return ResponseEntity.badRequest().body(
                new ErrorResponseDto(AEMET_INVALID_DATA.name(), e.getMessage())
        );
    }
}
