package br.com.web1.noticias.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cat_categoria", schema = "public")
public class CategoriaEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_nr_id")
    private Long catNrId;

    @Column(name = "cat_tx_nome")
    private String catTxNome;

    @Column(name = "cat_tx_url")
    private String catTxUrl;

    @Column(name = "cat_bl_editoria")
    private boolean catBlEditoria;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
    private List<NoticiaEntidade> noticias ;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
    private List<CategoriaUsuarioEntidade> categoriaUsuarios;



}
