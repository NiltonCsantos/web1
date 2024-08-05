package br.com.web1.noticias.controller.noticia;

import br.com.web1.noticias.service.noticias.NoticiasService;
import br.com.web1.noticias.service.noticias.dto.NoticiaDto;
import br.com.web1.noticias.service.noticias.form.NoticiasFiltroForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/noticias")
@RequiredArgsConstructor
public class NoticiasController {

    private final NoticiasService noticiasService;

    @GetMapping()
    ResponseEntity<Page<NoticiaDto>> buscarsNoticiais(NoticiasFiltroForm noticiasFiltroForm, Pageable pageable){
        var noticiaDto = noticiasService.buscarNoticias(noticiasFiltroForm, pageable);
        return ResponseEntity.ok(noticiaDto);
    }


}
