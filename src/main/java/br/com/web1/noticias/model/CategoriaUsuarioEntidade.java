package br.com.web1.noticias.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cau_categoria_usuario", schema = "public")
public class CategoriaUsuarioEntidade {
    @Column(name = "cau_nr_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cauNrId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cat_nr_id")
    private CategoriaEntidade categoria;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usu_nr_id")
    private UsuarioEntidade usuario;


}
