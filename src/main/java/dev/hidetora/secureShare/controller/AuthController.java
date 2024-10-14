package dev.hidetora.secureShare.controller;

import dev.hidetora.secureShare.dto.ApiResponse;
import dev.hidetora.secureShare.dto.LoginDto;
import dev.hidetora.secureShare.dto.RegisterUserDto;
import dev.hidetora.secureShare.dto.UserDto;
import dev.hidetora.secureShare.service.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("signin")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterUserDto registerUserDto) throws Exception {
        UserDto registeredUser = authService.registerUser(registerUserDto);
        Map<String, Object> response = new HashMap<>();
        response.put("data", registeredUser);
        ApiResponse apiResponse = new ApiResponse(
                HttpStatus.OK.name(),
                HttpStatus.OK,
                "User registered successfully",
                Instant.now(),
                response
        );
        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping("login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        Map<String, Object> response = new HashMap<>();
        response.put("data", token);
        ApiResponse apiResponse = new ApiResponse(
                HttpStatus.OK.name(),
                HttpStatus.OK,
                "User registered successfully",
                Instant.now(),
                response
        );
        return ResponseEntity.ok().body(apiResponse);
    }
}
