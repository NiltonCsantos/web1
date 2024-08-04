package br.com.web1.noticias.repository;

import br.com.web1.noticias.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositoy extends JpaRepository<Categoria, Long> {


}
