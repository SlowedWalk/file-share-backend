package dev.hidetora.secureShare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;

public record LoginDto(
        @Email(message = "valid email required")
        String email,
        @Min(value = 6, message = "Password must be at least 6 characters")
        String password
) { }
