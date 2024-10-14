package dev.hidetora.secureShare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "file")
public class AppFile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String fileName;
    private long fileSize;
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser user;
    @Lob
    @Column(name = "encrypted_aes_key", columnDefinition = "BLOB")
    private byte[] encryptedAESKey;
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] encryptedFile;
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] iv;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;
}
