package dev.hidetora.secureShare.service;

import dev.hidetora.secureShare.dto.UserDto;
import dev.hidetora.secureShare.entity.AppUser;

public interface AppUserService {
    AppUser saveUser(AppUser user);
    UserDto updateUserName(String userId, String name);
    String saveUserKey(String userId, String publicKey);
    UserDto searchByEmail(String email);
    UserDto getUser(String email);
    String updateUserPassword(String userId, String oldPassword, String newPassword);
}
