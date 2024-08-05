package br.com.web1.noticias.service.noticias.impl;

import br.com.web1.noticias.model.CategoriaEntidade;
import br.com.web1.noticias.model.NoticiaEntidade;
import br.com.web1.noticias.repository.NoticiaRepository;
import br.com.web1.noticias.service.noticias.NoticiasService;
import br.com.web1.noticias.service.noticias.dto.NoticiaDto;
import br.com.web1.noticias.service.noticias.form.NoticiasFiltroForm;
import br.com.web1.noticias.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class NoticiaServiceImpl implements NoticiasService {

    private final NoticiaRepository noticiaRepository;

    private final UsuarioService usuarioService;

    @Override
    public Page<NoticiaDto> buscarNoticias(NoticiasFiltroForm noticiasFiltroForm, Pageable pageable) {


        var usuario=usuarioService.buscarUsuarioAutenticado();

        var listaCategoriaUsuario = usuario.getCategorias();

        if (listaCategoriaUsuario.isEmpty()){
            return noticiaRepository.findAllNoticiasByFilro(noticiasFiltroForm, pageable).map(NoticiaDto::new);
        }else{

            var listaCategorias = new ArrayList<CategoriaEntidade>();

            listaCategoriaUsuario.forEach(categoriaUsuario ->
                    listaCategorias.add(categoriaUsuario.getCategoria())
                    );

            return noticiaRepository.findAllByCategoriaIn(listaCategorias, pageable).map(NoticiaDto::new);


        }

    }
}
