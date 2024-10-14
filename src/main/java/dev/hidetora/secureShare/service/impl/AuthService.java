package dev.hidetora.secureShare.service.impl;

import dev.hidetora.secureShare.dto.LoginDto;
import dev.hidetora.secureShare.dto.RegisterUserDto;
import dev.hidetora.secureShare.dto.UserDto;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;

public interface AuthService {
    UserDto registerUser(RegisterUserDto registerUserDto) throws Exception;
    String login(LoginDto loginDto);
}
