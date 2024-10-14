package dev.hidetora.secureShare.service.impl;

import dev.hidetora.secureShare.config.CustomPasswordEncoder;
import dev.hidetora.secureShare.dto.LoginDto;
import dev.hidetora.secureShare.dto.RegisterUserDto;
import dev.hidetora.secureShare.dto.UserDto;
import dev.hidetora.secureShare.entity.AppUser;
import dev.hidetora.secureShare.repository.AppUserRepository;
import dev.hidetora.secureShare.service.AppUserService;
import dev.hidetora.secureShare.utils.Constants;
import dev.hidetora.secureShare.utils.RSAKeyGenerator;
import dev.hidetora.secureShare.utils.RSAUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.FileSystem;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Base64;

import static dev.hidetora.secureShare.utils.FileUtil.readPublicKeyFromFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AppUserService appUserService;
    private final AppUserRepository appUserRepository;
    private final CustomPasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(RegisterUserDto registerUserDto) throws Exception {
        KeyPair keyPair = RSAKeyGenerator.generateKeyPair();

        AppUser newAppUser = AppUser.builder()
                .username(registerUserDto.name())
                .email(registerUserDto.email())
                .password(passwordEncoder.encode(registerUserDto.password()))
                .publicKey("")
                .build();
        AppUser appUser = appUserService.saveUser(newAppUser);

        // create the private key directory if it doesn't exist
        String privateKeyPemFilePath = Constants.PRIVATE_KEYS_DIR + "/" + appUser.getId().toString() + ".pem";
        PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(privateKeyPemFilePath)));
        pemWriter.writeObject(new PemObject("PRIVATE KEY", keyPair.getPrivate().getEncoded()));
        pemWriter.close();

        File tempFile = File.createTempFile("public_key", ".txt");
        try (FileWriter fileWriter = new FileWriter(tempFile, true)) {
            fileWriter.write(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
        }
        String publicKeyFromFile = readPublicKeyFromFile(tempFile.getPath());

        newAppUser.setPublicKey(publicKeyFromFile);
        log.info("PUBLIC KEY: {}", publicKeyFromFile);

        AppUser savedUser = appUserRepository.save(newAppUser);
        tempFile.delete();
        return UserDto.toDto(savedUser);
    }

    @Override
    public String login(LoginDto loginDto) {
        return null;
    }
}
