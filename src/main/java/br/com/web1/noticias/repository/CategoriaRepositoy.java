package br.com.web1.noticias.repository;

import br.com.web1.noticias.model.CategoriaEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepositoy extends JpaRepository<CategoriaEntidade, Long> {

    @Query(nativeQuery = true,
        value = "select cat.cat_tx_nome from public.cat_categoria cat"
    )
    List<String> buscarNomeCategorias();

    @Query(nativeQuery = true,
             value = """
                     select cat.* from public.cat_categoria cat
                     where upper(cat.cat_tx_nome) = :#{#catTxNome.trim().toUpperCase()}
                     """)
    Optional<CategoriaEntidade> findByCatTxNome(String catTxNome);

}
