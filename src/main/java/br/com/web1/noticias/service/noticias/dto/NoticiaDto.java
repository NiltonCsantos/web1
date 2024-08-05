package br.com.web1.noticias.service.noticias.dto;

import br.com.web1.noticias.model.CategoriaEntidade;
import br.com.web1.noticias.model.NoticiaEntidade;

public record NoticiaDto(

            String notTxNome,
            String notTxDescricao

) {
    public NoticiaDto(NoticiaEntidade noticiaEntidade) {
        this(noticiaEntidade.getNotTxNome(), noticiaEntidade.getNotTxDescricao());
    }
}
