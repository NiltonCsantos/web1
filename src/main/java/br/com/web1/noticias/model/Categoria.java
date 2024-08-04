package br.com.web1.noticias.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "cat_categoria", schema = "public")
@Getter
@Setter
public class Categoria {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nome;
    private Long cat_Id;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
    List<Noticia> noticias ;



}
