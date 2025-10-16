package com.hng.stagezero.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<Map<String, Object>> handleWebClientResponseException(WebClientResponseException e) {
        log.warn(" External API returned error: {} - {}", e.getStatusCode(), e.getStatusText());
        return buildErrorResponse("External API error: " + e.getStatusText(), HttpStatus.valueOf(e.getStatusCode().value()));
    }

    @ExceptionHandler(WebClientRequestException.class)
    public ResponseEntity<Map<String, Object>> handleWebClientRequestException(WebClientRequestException e) {
        log.error(" Network error calling external API: {}", e.getMessage());
        return buildErrorResponse("Network error communicating with external API", HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException e) {
        if (e.getCause() instanceof TimeoutException) {
            log.warn(" Timeout while calling external API: {}", e.getMessage());
            return buildErrorResponse("External API request timed out", HttpStatus.REQUEST_TIMEOUT);
        }

        log.error(" Unexpected server error: {}", e.getMessage(), e);
        return buildErrorResponse("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message, HttpStatus status) {
        return ResponseEntity.status(status)
                .body(Map.of(
                        "timestamp", Instant.now(),
                        "status", status.value(),
                        "error", status.getReasonPhrase(),
                        "message", message
                ));
    }
}

