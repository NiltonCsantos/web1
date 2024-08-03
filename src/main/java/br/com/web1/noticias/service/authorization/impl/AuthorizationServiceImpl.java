package br.com.web1.noticias.service.authorization.impl;

import br.com.web1.noticias.repository.UsuarioRepository;
import br.com.web1.noticias.service.authorization.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usuarioRepository.findByUsuTxEmail(login);
    }
}
