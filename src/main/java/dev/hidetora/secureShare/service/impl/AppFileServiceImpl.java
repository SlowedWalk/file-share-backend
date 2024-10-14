package dev.hidetora.secureShare.service.impl;

import dev.hidetora.secureShare.dto.FileUploadDto;
import dev.hidetora.secureShare.entity.AppFile;
import dev.hidetora.secureShare.entity.AppUser;
import dev.hidetora.secureShare.repository.AppFileRepository;
import dev.hidetora.secureShare.repository.AppUserRepository;
import dev.hidetora.secureShare.service.AppFileService;
import dev.hidetora.secureShare.utils.AESUtil;
import dev.hidetora.secureShare.utils.Constants;
import dev.hidetora.secureShare.utils.FileUtil;
import dev.hidetora.secureShare.utils.RSAUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.SecretKey;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

@Service
@RequiredArgsConstructor
public class AppFileServiceImpl implements AppFileService {
    private final AppFileRepository appFileRepository;
    private final AppUserRepository appUserRepository;

    @Override
    public String saveEncryptedFile(MultipartFile file) {
        return "";
    }

    @Override
    public Page<AppFile> getSharedFiles(String userId, int page, int size) {
        return null;
    }

    @Override
    public AppFile getFile(String fileId) {
        return null;
    }

    @Override
    public String deleteExpiredFiles() {
        return "";
    }

    @Override
    public String uploadFile(FileUploadDto fileUploadDto, MultipartFile multipartFile) {
        appUserRepository.findById(fileUploadDto.userId()).ifPresent(appUser -> {
            AppUser recipientUser = appUserRepository.findByEmail(fileUploadDto.recipientEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            String publicKeyStr = recipientUser.getPublicKey();

            String fileName = multipartFile.getOriginalFilename();
            long fileSize = multipartFile.getSize();
            byte[] fileData;
            try {
                fileData = multipartFile.getBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            File output = new File(Constants.ENCRYPTED_FILES_FOLDER);
            try {
                // 1. Generate AES key
                SecretKey key = AESUtil.generateAESKey();

                // 2. Encrypt file content
                byte[] encryptedContent = AESUtil.encryptFile(key, fileData);

                // 3. Encrypt AES key
//            PublicKey publicKey = RSAUtil.getPublicKey(Base64.getDecoder().decode(FileUtil.readBytesFromFile(publicKeyFile)));
                PublicKey publicKey = RSAUtil.parsePublicKey(publicKeyStr);

                byte[] encryptedKey = RSAUtil.encryptKey(publicKey, key.getEncoded());

                // 4. Attach encrypted key to file
                byte[] fileOutputContent = FileUtil.combineBytes(encryptedKey, encryptedContent);

                // 5. Write to output
                FileUtil.writeToFile(output, fileOutputContent);
                AppFile appFile = AppFile.builder()
                        .fileName(fileName)
                        .fileSize(fileSize)
                        .user(appUser)
                        .encryptedFile(fileOutputContent)
                        .build();
                appFileRepository.save(appFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return "file uploaded successfully";
    }
}
