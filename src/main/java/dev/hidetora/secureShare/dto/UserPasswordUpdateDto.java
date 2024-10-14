package dev.hidetora.secureShare.dto;

import jakarta.validation.constraints.NotNull;

public record UserPasswordUpdateDto(
        String userId,
        @NotNull(message = "New password is required.")
        String newPassword,
        @NotNull(message = "Old password is required.")
        String oldPassword
) { }
