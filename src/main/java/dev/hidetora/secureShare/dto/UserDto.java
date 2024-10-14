package dev.hidetora.secureShare.dto;

import dev.hidetora.secureShare.entity.AppUser;
import org.apache.catalina.User;

import java.time.Instant;

public record UserDto(
        String id,
        String username,
        String email,
        String publicKey,
        Instant createdAt,
        Instant updatedAt
) {
    public static UserDto toDto(AppUser user) {
        return new UserDto(
                user.getId().toString(),
                user.getUsername(),
                user.getEmail(),
                user.getPublicKey(),
                user.getCreatedAt(),
                user.getCreatedAt()
        );
    }
}
