package dev.hidetora.secureShare.dto;

import java.time.Instant;

public record UserReceiveFileDto(
        String fileId,
        String fileName,
        String senderEmail,
        Instant expirationDate,
        Instant createdAt
) { }
