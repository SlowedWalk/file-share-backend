package dev.hidetora.secureShare.config;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8().encode(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8().matches(rawPassword.toString(), encodedPassword);
    }
}

