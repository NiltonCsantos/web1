package br.com.web1.noticias.service.token;

import br.com.web1.noticias.model.UsuarioEntidade;

public interface TokenService {
    String gerarAcessToken(UsuarioEntidade usuarioEntidade);
    String validarToken(String token);
    String gerarRefreshToken(UsuarioEntidade usuarioEntidade);
    String gerarTokenTemporario(UsuarioEntidade usuarioEntidade);
}
