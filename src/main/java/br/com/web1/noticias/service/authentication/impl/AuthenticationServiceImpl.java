package br.com.web1.noticias.service.authentication.impl;

import br.com.web1.noticias.exception.NotFoundException;
import br.com.web1.noticias.exception.RegistredException;
import br.com.web1.noticias.model.UsuarioEntidade;
import br.com.web1.noticias.repository.UsuarioRepository;
import br.com.web1.noticias.service.authentication.AuthenticationService;
import br.com.web1.noticias.service.authentication.dto.AuthenticationDto;
import br.com.web1.noticias.service.usuario.dto.Usuariodto;
import br.com.web1.noticias.service.usuario.form.UsuarioForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuariodto criarConta(UsuarioForm form) {

       var usuairoFind = usuarioRepository.findByUsuTxLogin(form.usuTxlogin());

       if (usuairoFind.isPresent()){
           throw  new RegistredException("usuário");
       }


       String encryptedPassword= new BCryptPasswordEncoder().encode(form.usuTxSenha());

       var usuario = UsuarioEntidade.builder()
               .usuTxEmail(form.usuTxEmail())
               .usuTxNome(form.usuTxNome())
               .usuTxLogin(form.usuTxlogin())
               .usuDtNascimento(form.usutxDtNascimento())
               .usuTxSenha(encryptedPassword)
               .usutxTelefone(form.usuTxTelefone())
               .build();

        this.usuarioRepository.save(usuario);

        return  new Usuariodto(usuario);
    }

    @Override
    public AuthenticationDto login() {
        return null;
    }

    @Override
    public String redefinirSenha() {
        return null;
    }
}
