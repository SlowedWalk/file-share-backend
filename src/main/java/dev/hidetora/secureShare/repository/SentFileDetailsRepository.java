package dev.hidetora.secureShare.repository;

import dev.hidetora.secureShare.dto.UserSendFileDto;
import dev.hidetora.secureShare.entity.SentFileDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SentFileDetailsRepository extends JpaRepository<SentFileDetails, UUID> {
    @Query(value = "select '*' from SentFileDetails au where au.user.id = ?1")
    Page<UserSendFileDto> findAllSentFiles(UUID userId, PageRequest pageRequest);
}
