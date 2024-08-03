package br.com.web1.noticias.repository;

import br.com.web1.noticias.model.UsuarioEntidade;
import br.com.web1.noticias.service.usuario.dto.Usuariodto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntidade, Long> {

    UserDetails findByUsuTxEmail(@Param("usuTxEmail") String usuTxEmail);

    @Query(nativeQuery = true,
            value = """
                    select 
                           usu.*
                           from public.usu_usuario usu
                           where upper(usu.usu_tx_login) = :#{#usuTxLogin.trim().toUpperCase()}
                    """
    )
    Optional<UsuarioEntidade> findByUsuTxLogin(String usuTxLogin);



}
