package br.com.web1.noticias.service.usuario;

import br.com.web1.noticias.model.UsuarioEntidade;

import java.util.List;

public interface UsuarioService {
    UsuarioEntidade buscarUsuarioAutenticado();

    List<String> associarUsuarioCategoria(List<Long> listCatNrId);
}
