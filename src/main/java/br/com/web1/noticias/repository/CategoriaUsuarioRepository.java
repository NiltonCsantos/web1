package br.com.web1.noticias.repository;

import br.com.web1.noticias.model.CategoriaUsuarioEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaUsuarioRepository extends JpaRepository<CategoriaUsuarioEntidade, Long> {
}
