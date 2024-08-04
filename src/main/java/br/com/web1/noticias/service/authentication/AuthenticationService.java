package br.com.web1.noticias.service.authentication;

import br.com.web1.noticias.service.authentication.dto.AuthenticationDto;
import br.com.web1.noticias.service.authentication.form.AuthenticationForm;
import br.com.web1.noticias.service.authentication.form.DadosRecuperarSenhaAuthenticationForm;
import br.com.web1.noticias.service.usuario.dto.Usuariodto;
import br.com.web1.noticias.service.usuario.form.UsuarioForm;

public interface AuthenticationService {

    Usuariodto criarConta(UsuarioForm form);

    AuthenticationDto login(AuthenticationForm form);

    Usuariodto confirmarConta(String tokenTemp);

    Usuariodto redefinirSenha(DadosRecuperarSenhaAuthenticationForm form);

}
