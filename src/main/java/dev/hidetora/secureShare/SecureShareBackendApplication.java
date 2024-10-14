package dev.hidetora.secureShare;

import dev.hidetora.secureShare.config.RsaKeyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@EnableConfigurationProperties({RsaKeyConfig.class})
public class SecureShareBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureShareBackendApplication.class, args);
	}

}
