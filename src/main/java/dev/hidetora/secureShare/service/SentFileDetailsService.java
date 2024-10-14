package dev.hidetora.secureShare.service;

import dev.hidetora.secureShare.dto.UserSendFileDto;
import org.springframework.data.domain.Page;

public interface SentFileDetailsService {
    Page<UserSendFileDto> getSentFiles(String userId, int page, int size);
}
