package br.com.web1.noticias.service.categorias.dto;

import br.com.web1.noticias.model.CategoriaUsuarioEntidade;
import br.com.web1.noticias.model.NoticiaEntidade;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class CategoriaGrupoDto <T> implements CategoriaDadosBasicosDto{

    private Long catNrId;

    private String catTxNome;

    private String catTxUrl;

    private Boolean catBlEditoria;

    private T catTxGrupo;

}
