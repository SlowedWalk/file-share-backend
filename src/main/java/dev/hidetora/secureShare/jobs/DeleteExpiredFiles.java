package dev.hidetora.secureShare.jobs;

import dev.hidetora.secureShare.repository.AppFileRepository;
import dev.hidetora.secureShare.repository.SharedLinkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeleteExpiredFiles {
    private final AppFileRepository appFileRepository;
    private final SharedLinkRepository sharedLinkRepository;

    @Scheduled(cron = "0 0 * * * *")
    public void deleteExpiredFiles() {
      log.info("Running Scheduled task to delete expired files...");
      sharedLinkRepository.findAll().stream()
              .filter(sharedLink -> sharedLink.getExpirationDate().isBefore(Instant.now()))
              .forEach(sharedLink -> appFileRepository.delete(sharedLink.getFile()));
      log.info("Successfully deleted expired files and their shared links.");
    }
}
