package dev.hidetora.secureShare.controller;

import dev.hidetora.secureShare.dto.ApiResponse;
import dev.hidetora.secureShare.dto.FileUploadDto;
import dev.hidetora.secureShare.dto.UserReceiveFileDto;
import dev.hidetora.secureShare.dto.UserSendFileDto;
import dev.hidetora.secureShare.repository.SharedLinkRepository;
import dev.hidetora.secureShare.service.AppFileService;
import dev.hidetora.secureShare.service.ReceivedFileDetailsService;
import dev.hidetora.secureShare.service.SentFileDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/file-share")
@RequiredArgsConstructor
public class FileShareController {
    private final SentFileDetailsService sentFileDetailsService;
    private final ReceivedFileDetailsService receivedFileDetailsService;
    private final AppFileService appFileService;

    @GetMapping("sent")
    public ResponseEntity<ApiResponse> sent(@RequestParam String userId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
        Page<UserSendFileDto> sentFiles = sentFileDetailsService.getSentFiles(userId, page, size);
        Map<String, Object> response = new HashMap<>();
        response.put("data", sentFiles);
        ApiResponse apiResponse = new ApiResponse(
                HttpStatus.OK.name(),
                HttpStatus.OK,
                "User sent files fetched successfully",
                Instant.now(),
                response
        );
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("received")
    public ResponseEntity<ApiResponse> received(@RequestParam String userId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
        Page<UserReceiveFileDto> receivedFiles = receivedFileDetailsService.getReceivedFiles(userId, page, size);
        Map<String, Object> response = new HashMap<>();
        response.put("data", receivedFiles);
        ApiResponse apiResponse = new ApiResponse(
                HttpStatus.OK.name(),
                HttpStatus.OK,
                "User received files fetched successfully",
                Instant.now(),
                response
        );
        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping("upload/{userId}")
    public ResponseEntity<ApiResponse> uploadFile(@ModelAttribute MultipartFile multipartFile, @RequestBody FileUploadDto fileUploadDto) {
        appFileService.uploadFile(fileUploadDto, multipartFile);
        return null;
    }
}
