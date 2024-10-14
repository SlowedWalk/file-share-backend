package dev.hidetora.secureShare.controller;

import dev.hidetora.secureShare.dto.ApiResponse;
import dev.hidetora.secureShare.dto.NameUpdateDto;
import dev.hidetora.secureShare.dto.UserDto;
import dev.hidetora.secureShare.dto.UserPasswordUpdateDto;
import dev.hidetora.secureShare.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;

    @GetMapping("me/{userEmail}")
    public ResponseEntity<ApiResponse> getMe(@PathVariable String userEmail) {
        UserDto user = appUserService.getUser(userEmail);
        Map<String, Object> response = new HashMap<>();
        response.put("data", user);
        ApiResponse apiResponse = new ApiResponse(
                HttpStatus.OK.name(),
                HttpStatus.OK,
                "User retrieved",
                Instant.now(),
                response
        );
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("search-email/{userEmail}")
    public ResponseEntity<ApiResponse> SearchByEmail(@PathVariable String userEmail) {
        UserDto user = appUserService.getUser(userEmail);
        Map<String, Object> response = new HashMap<>();
        response.put("data", user);
        ApiResponse apiResponse = new ApiResponse(
                HttpStatus.OK.name(),
                HttpStatus.OK,
                "User retrieved",
                Instant.now(),
                response
        );
        return ResponseEntity.ok().body(apiResponse);
    }

    @PatchMapping("name")
    public ResponseEntity<ApiResponse> updateUserName(@RequestBody NameUpdateDto nameUpdateDto) {
        UserDto userDto = appUserService.updateUserName(nameUpdateDto.userId(), nameUpdateDto.name());
        Map<String, Object> response = new HashMap<>();
        response.put("data", userDto);
        ApiResponse apiResponse = new ApiResponse(
                HttpStatus.OK.name(),
                HttpStatus.OK,
                "Username updated successfully",
                Instant.now(),
                response
        );
        return ResponseEntity.ok().body(apiResponse);
    }

    @PatchMapping("password")
    public ResponseEntity<ApiResponse> updateUserPassword(@RequestBody UserPasswordUpdateDto passwordUpdateDto) {
        String userDto = appUserService.updateUserPassword(passwordUpdateDto.userId(), passwordUpdateDto.oldPassword(), passwordUpdateDto.newPassword());
        ApiResponse apiResponse = new ApiResponse(
                HttpStatus.OK.name(),
                HttpStatus.OK,
                "User password updated successfully",
                Instant.now(),
                null
        );
        return ResponseEntity.ok().body(apiResponse);
    }

}
