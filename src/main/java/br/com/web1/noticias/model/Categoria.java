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
public class Categoria {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_nr_id")
    private Long catNrId;

    @Column(name = "cat_tx_nome")
    private String catTxNome;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
    List<Noticia> noticias ;



}
