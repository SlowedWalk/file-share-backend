package dev.hidetora.secureShare.dto;

import java.time.Instant;

public record FilterUserDto(
        String id,
        String name,
        String email,
        String publicKey,
        Instant createdAt,
        Instant updatedAt
) { }
