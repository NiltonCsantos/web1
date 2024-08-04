package br.com.web1.noticias.repository;

import br.com.web1.noticias.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticiaRepository extends JpaRepository<Noticia, Long>{

}
