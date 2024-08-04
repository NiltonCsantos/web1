package br.com.web1.noticias.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usu_usuario", schema = "public")
public class UsuarioEntidade implements UserDetails {

    @Id
    @Column(name = "usu_nr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuNrId;

    @Column(name = "usu_tx_nome")
    private String usuTxNome;
    @Column(name = "usu_tx_login", unique = true)
    private String usuTxLogin;
    @Column(name = "usu_tx_telefone", unique = true)
    private String usutxTelefone;
    @Column(name = "usu_tx_senha")
    private String usuTxSenha;
    @Column(name = "usu_tx_email", unique = true)
    private String usuTxEmail;
    @Column(name = "usu_dt_nascimento")
    private LocalDate usuDtNascimento;
    @Column(name = "usu_bl_ativo")
    private boolean usuBlAtivo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.usuTxSenha;
    }

    @Override
    public String getUsername() {
        return this.usuTxLogin;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.usuBlAtivo;
    }
}
