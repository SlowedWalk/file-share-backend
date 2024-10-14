package dev.hidetora.secureShare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record RegisterUserDto(
        String name,
        @Email(message = "valid email required")
        String email,
        @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")
        String password
) { }
