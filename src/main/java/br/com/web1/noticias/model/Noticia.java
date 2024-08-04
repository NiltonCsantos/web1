package br.com.web1.noticias.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Noti_Noticias", schema = "public")
@Getter
@Setter
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private String nome;
    private String descricao;
    private Long noticia_Id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_categoria_nr_id",nullable = false)
    private Categoria categoria;



}
