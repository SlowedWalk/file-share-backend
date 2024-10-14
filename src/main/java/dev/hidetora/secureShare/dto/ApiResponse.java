package dev.hidetora.secureShare.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse(
        String statusCode,
        HttpStatus httpStatus,
        String message,
        Instant timestamp,
        Map<String, Object> data
) { }
