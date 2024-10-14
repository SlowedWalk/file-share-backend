package dev.hidetora.secureShare.repository;

import dev.hidetora.secureShare.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findAppRoleByName(String roleName);
}
