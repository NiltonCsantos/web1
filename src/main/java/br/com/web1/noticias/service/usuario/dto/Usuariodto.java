package br.com.web1.noticias.service.usuario.dto;

import br.com.web1.noticias.model.UsuarioEntidade;

public record Usuariodto(

        String usuTxNome,
        String usuTxEmail

) {
    public Usuariodto(UsuarioEntidade usuario) {
        this(usuario.getUsuTxNome(), usuario.getUsuTxEmail());
    }
}
