package br.com.web1.noticias.service.authorization;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailsService extends UserDetailsService {
    String getEmail();
}
