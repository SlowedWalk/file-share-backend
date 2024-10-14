package dev.hidetora.secureShare.service.impl;

import dev.hidetora.secureShare.dto.UserSendFileDto;
import dev.hidetora.secureShare.repository.SentFileDetailsRepository;
import dev.hidetora.secureShare.service.SentFileDetailsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SentFileDetailsServiceImpl implements SentFileDetailsService {
    private final SentFileDetailsRepository sentFileDetailsRepository;

    @Override
    @Transactional
    public Page<UserSendFileDto> getSentFiles(String userId, int page, int size) {
        return sentFileDetailsRepository.findAllSentFiles(UUID.fromString(userId), PageRequest.of(page, size));
    }
}
