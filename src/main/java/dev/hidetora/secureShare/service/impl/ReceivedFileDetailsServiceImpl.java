package dev.hidetora.secureShare.service.impl;

import dev.hidetora.secureShare.dto.UserReceiveFileDto;
import dev.hidetora.secureShare.service.ReceivedFileDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceivedFileDetailsServiceImpl implements ReceivedFileDetailsService {
    @Override
    public Page<UserReceiveFileDto> getReceivedFiles(String userId, int page, int size) {
        return null;
    }
}
