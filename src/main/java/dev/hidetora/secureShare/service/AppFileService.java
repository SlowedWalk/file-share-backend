package dev.hidetora.secureShare.service;

import dev.hidetora.secureShare.dto.FileUploadDto;
import dev.hidetora.secureShare.entity.AppFile;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface AppFileService {
    String saveEncryptedFile(MultipartFile file);
    Page<AppFile> getSharedFiles(String userId, int page, int size);
    AppFile getFile(String fileId);
    String deleteExpiredFiles();

    String uploadFile(FileUploadDto fileUploadDto, MultipartFile multipartFile);
}
