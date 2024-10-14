package dev.hidetora.secureShare.dto;

import jakarta.validation.constraints.NotNull;

public record RetrieveFileDto(
        @NotNull(message = "Shared id is required.")
        String sharedId,
        @NotNull(message = "Password is required.")
        String password
) {
}
