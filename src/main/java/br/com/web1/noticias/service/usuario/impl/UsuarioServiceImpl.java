package br.com.web1.noticias.service.usuario.impl;

import br.com.web1.noticias.exception.NotFoundException;
import br.com.web1.noticias.model.CategoriaEntidade;
import br.com.web1.noticias.model.CategoriaUsuarioEntidade;
import br.com.web1.noticias.model.UsuarioEntidade;
import br.com.web1.noticias.repository.CategoriaRepositoy;
import br.com.web1.noticias.repository.CategoriaUsuarioRepository;
import br.com.web1.noticias.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final CategoriaUsuarioRepository categoriaUsuarioRepository;
    private final CategoriaRepositoy categoriaRepositoy;
    @Override
    public UsuarioEntidade buscarUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UsuarioEntidade) {
            return (UsuarioEntidade) authentication.getPrincipal();
        }
        throw new NotFoundException("Usuairo");
    }

    @Override
    public List<String> associarUsuarioCategoria(List<Long> listCatNrId) {
        var usuario = buscarUsuarioAutenticado();

        var listaCatTxNome = new ArrayList<String>();

        listCatNrId.forEach(catNrId -> {

            var categoria = categoriaRepositoy.findById(catNrId)
                    .orElseThrow(() -> new NotFoundException("Categoria"));

            var categoriaUsuario = CategoriaUsuarioEntidade.builder()
                    .usuario(usuario)
                    .categoria(categoria)
                    .build();

            listaCatTxNome.add(categoria.getCatTxNome());

            categoriaUsuarioRepository.save(categoriaUsuario);
        });

        return listaCatTxNome;
    }
}
