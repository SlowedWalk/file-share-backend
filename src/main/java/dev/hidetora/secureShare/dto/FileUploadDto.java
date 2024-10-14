package dev.hidetora.secureShare.dto;

import java.time.Instant;

public record FileUploadDto(
        String userId,
        String recipientEmail,
        String password,
        Instant expirationDate
) { }
