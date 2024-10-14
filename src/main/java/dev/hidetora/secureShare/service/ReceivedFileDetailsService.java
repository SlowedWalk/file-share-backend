package dev.hidetora.secureShare.service;

import dev.hidetora.secureShare.dto.UserReceiveFileDto;
import org.springframework.data.domain.Page;

public interface ReceivedFileDetailsService {
    Page<UserReceiveFileDto> getReceivedFiles(String userId, int page, int size);
}
