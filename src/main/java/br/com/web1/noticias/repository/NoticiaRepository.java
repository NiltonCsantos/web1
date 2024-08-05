package br.com.web1.noticias.repository;

import br.com.web1.noticias.model.CategoriaEntidade;
import br.com.web1.noticias.model.NoticiaEntidade;
import br.com.web1.noticias.service.noticias.form.NoticiasFiltroForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticiaRepository extends JpaRepository<NoticiaEntidade, Long>{

    @Query(nativeQuery = true,
    value = """
           select no.not_tx_url  from public.not_noticias no
           order by no.not_dt_publicacao desc
            """)
    List<String> findAllByNotTxUrl();

    @Query(nativeQuery = true,
            value = """
                select no.* from public.not_noticias no
                inner join public.cat_categoria cat on cat.cat_nr_id = no.cat_categoria_nr_id
                where (upper(cat.cat_tx_nome) = 'G1')
                and(:#{#filtro.notTxNome() == null} or UPPER(no.not_tx_nome) LIKE UPPER(CONCAT('%', COALESCE(:#{#filtro.notTxNome()}, ''), '%')))
                and (:#{#filtro.catNrId() == null} or no.cat_categoria_nr_id = :#{#filtro.catNrId()})
                order by no.not_dt_publicacao desc
       """)

    Page<NoticiaEntidade> findAllNoticiasByFilro(NoticiasFiltroForm filtro, Pageable pageable);
    Page<NoticiaEntidade> findAllByCategoriaIn(@Param("categorias")List<CategoriaEntidade> categorias, Pageable pageable);
}
