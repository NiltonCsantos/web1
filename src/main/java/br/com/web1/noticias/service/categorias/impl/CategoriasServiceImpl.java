package br.com.web1.noticias.service.categorias.impl;

import br.com.web1.noticias.repository.CategoriaRepositoy;
import br.com.web1.noticias.service.categorias.CategoriasService;
import br.com.web1.noticias.service.categorias.dto.CategoriaGrupoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriasServiceImpl implements CategoriasService {

    private final CategoriaRepositoy categoriaRepositoy;

    @Override
    public CategoriaGrupoDto listarCategorias() {
        return null;
    }
}
