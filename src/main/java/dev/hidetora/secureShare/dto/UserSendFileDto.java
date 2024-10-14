package dev.hidetora.secureShare.dto;

import java.time.Instant;

public record UserSendFileDto(
        String fileId,
        String fileName,
        String recipientEmail,
        Instant expirationDate,
        Instant createdAt
) { }
