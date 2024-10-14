package dev.hidetora.secureShare.repository;

import dev.hidetora.secureShare.entity.ReceivedFileDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReceivedFileDetailsRepository extends JpaRepository<ReceivedFileDetails, UUID> {
}
