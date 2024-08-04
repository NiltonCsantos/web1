package br.com.web1.noticias.service.usuario.dto;

import br.com.web1.noticias.model.UsuarioEntidade;
import lombok.Builder;

@Builder
public record Usuariodto(
        String usuTxLogin,
        String usuTxNome,
        String usuTxEmail,
        String usutxTelefone

) {
    public Usuariodto(UsuarioEntidade usuario) {
        this(usuario.getUsuTxLogin(),usuario.getUsuTxNome(), usuario.getUsuTxEmail(), usuario.getUsutxTelefone());
    }
}
