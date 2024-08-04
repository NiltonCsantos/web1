package br.com.web1.noticias.service.authorization.impl;

import br.com.web1.noticias.repository.UsuarioRepository;
import br.com.web1.noticias.service.authorization.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usuarioRepository.findByUsuTxLogin(login);
    }

    @Override
    public String getEmail() {
        return null;
    }
}
