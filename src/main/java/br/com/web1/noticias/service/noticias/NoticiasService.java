package br.com.web1.noticias.service.noticias;

import br.com.web1.noticias.service.noticias.dto.NoticiaDto;
import br.com.web1.noticias.service.noticias.form.NoticiasFiltroForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticiasService {

    Page<NoticiaDto> buscarNoticias( NoticiasFiltroForm noticiasFiltroForm, Pageable pageable);


}
