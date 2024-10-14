package dev.hidetora.secureShare.init;

import dev.hidetora.secureShare.config.CustomPasswordEncoder;
import dev.hidetora.secureShare.entity.AppRole;
import dev.hidetora.secureShare.entity.AppUser;
import dev.hidetora.secureShare.repository.AppRoleRepository;
import dev.hidetora.secureShare.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationStartRunner implements ApplicationRunner {
    private final AppRoleRepository appRoleRepository;
    private final AppUserRepository appUserRepository;
    private final CustomPasswordEncoder customPasswordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        AppRole roleUser = AppRole.builder()
                .name("ROLE_USER")
                .build();

        AppRole roleAdmin = AppRole.builder()
                .name("ROLE_ADMIN")
                .build();

        appRoleRepository.saveAll(List.of(roleUser, roleAdmin));

        AppUser appUser = AppUser.builder()
                .username("admin")
                .password(customPasswordEncoder.encode("admin"))
                .appRole(appRoleRepository.findAppRoleByName("ROLE_ADMIN"))
                .publicKey("")
                .build();
        appUserRepository.save(appUser);

    }
}
