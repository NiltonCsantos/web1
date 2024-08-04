package br.com.web1.noticias.service.authentication.impl;

import br.com.web1.noticias.exception.ConflictException;
import br.com.web1.noticias.exception.NotFoundException;
import br.com.web1.noticias.exception.RegistredException;
import br.com.web1.noticias.model.UsuarioEntidade;
import br.com.web1.noticias.repository.UsuarioRepository;
import br.com.web1.noticias.service.authentication.AuthenticationService;
import br.com.web1.noticias.service.authentication.dto.AuthenticationDto;
import br.com.web1.noticias.service.authentication.form.AuthenticationForm;
import br.com.web1.noticias.service.authentication.form.DadosRecuperarSenhaAuthenticationForm;
import br.com.web1.noticias.service.authorization.CustomUserDetailsService;
import br.com.web1.noticias.service.mail.MailService;
import br.com.web1.noticias.service.mail.impl.MailServiceImpl;
import br.com.web1.noticias.service.token.TokenService;
import br.com.web1.noticias.service.usuario.dto.Usuariodto;
import br.com.web1.noticias.service.usuario.form.UsuarioForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsuarioRepository usuarioRepository;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final CustomUserDetailsService customUserDetailsService;

    private final MailService mailService;

    @Override
    public Usuariodto criarConta(UsuarioForm form) {

       var usuairoFind = usuarioRepository.buscarUsuTxLogin(form.usuTxLogin());

       if (usuairoFind.isPresent()){
           throw  new RegistredException("usuário");
       }


       String encryptedPassword= new BCryptPasswordEncoder().encode(form.usuTxSenha());

       var usuario = UsuarioEntidade.builder()
               .usuTxEmail(form.usuTxEmail())
               .usuTxNome(form.usuTxNome())
               .usuTxLogin(form.usuTxLogin())
               .usuDtNascimento(form.usutxDtNascimento())
               .usuTxSenha(encryptedPassword)
               .usutxTelefone(form.usuTxTelefone())
               .build();

        this.usuarioRepository.save(usuario);

        String tokenTemp = tokenService.gerarTokenTemporario(usuario);

        mailService.sendMail(usuario.getUsuTxEmail(), usuario.getUsuTxNome(), tokenTemp);

        return  new Usuariodto(usuario);
    }

    @Override
    public AuthenticationDto login(AuthenticationForm form) {

        UserDetails userDetails = usuarioRepository.findByUsuTxLogin(form.usuTxLogin());

        if (customUserDetailsService.loadUserByUsername(form.usuTxLogin()) == null) {
            throw new NotFoundException("Usuário não encontrado");
        }

        UsernamePasswordAuthenticationToken passwordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                form.usuTxLogin(), form.usuTxSenha());

        var auth = this.authenticationManager.authenticate(passwordAuthenticationToken);

        String acessToken = this.tokenService.gerarAcessToken((UsuarioEntidade) auth.getPrincipal());

        String refreshToken = this.tokenService.gerarRefreshToken((UsuarioEntidade) auth.getPrincipal());

        return  AuthenticationDto.builder()
                .acessToken(acessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public Usuariodto confirmarConta(String tokenTemp) {

        var usuTxLogin = tokenService.validarToken(tokenTemp);

        var usuario = this.usuarioRepository.buscarUsuTxLogin(usuTxLogin).orElseThrow(
                () -> new NotFoundException("Usuario")
        );

        usuario.setUsuBlAtivo(true);
        usuarioRepository.save(usuario);
        return  new Usuariodto(usuario);
    }

    @Override
    public Usuariodto redefinirSenha(DadosRecuperarSenhaAuthenticationForm form) {

        var usuario = usuarioRepository.findByUsuTxEmailAndUsuBlAtivo(form.usuTxEmail()).orElseThrow(
                () -> new NotFoundException("Usuario")
        );

        if (!form.usuDtNascimento().equals(usuario.getUsuDtNascimento())){
            throw new ConflictException("data de nascimento incorreta");
        }

        String encryptedPassword= new BCryptPasswordEncoder().encode(form.usuTxNovaSenha());

        usuario.setUsuTxSenha(encryptedPassword);

        usuarioRepository.save(usuario);

        return  new Usuariodto(usuario);

    }
}
