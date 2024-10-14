package dev.hidetora.secureShare.config.security;

import dev.hidetora.secureShare.entity.AppRole;
import dev.hidetora.secureShare.entity.AppUser;
import dev.hidetora.secureShare.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));

        AppRole role = user.getAppRole();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return new User(username, user.getPassword(), user.isEnabled(), true, user.isCredentialsNonExpired(), true, authorities);
    }
}
