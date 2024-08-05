package br.com.web1.noticias.controller.usario;

import br.com.web1.noticias.service.categorias.form.CategoriaForm;
import br.com.web1.noticias.service.usuario.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<List<String>> associarCategoriaAoUsuario(@RequestBody List<Long> listaCategorianrId) {
        var listaNomesCategorias = usuarioService.associarUsuarioCategoria(listaCategorianrId);
        return ResponseEntity.status(HttpStatus.CREATED).body(listaNomesCategorias);
    }


}
