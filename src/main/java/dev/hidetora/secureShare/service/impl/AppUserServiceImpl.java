package dev.hidetora.secureShare.service.impl;

import dev.hidetora.secureShare.dto.UserDto;
import dev.hidetora.secureShare.entity.AppUser;
import dev.hidetora.secureShare.repository.AppUserRepository;
import dev.hidetora.secureShare.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;

    @Override
    public AppUser saveUser(AppUser user) {
       return appUserRepository.save(user);
    }

    @Override
    public UserDto updateUserName(String userId, String name) {
        AppUser appUser = appUserRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        appUser.setUsername(name);
        AppUser saved = appUserRepository.save(appUser);
        return UserDto.toDto(saved);
    }

    @Override
    public String updateUserPassword(String userId, String oldPassword, String newPassword) {
        AppUser appUser = appUserRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // TODO: use bcrypt
        appUser.setPassword(oldPassword);
        return "Success";
    }

    @Override
    public String saveUserKey(String userId, String publicKey) {
        return "";
    }

    @Override
    public UserDto searchByEmail(String email) {
        return null;
    }

    @Override
    public UserDto getUser(String email) {
        AppUser appUser = appUserRepository.findByEmail(email).get();
        return UserDto.toDto(appUser);
    }
}
