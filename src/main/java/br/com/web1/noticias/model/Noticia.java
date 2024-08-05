package br.com.web1.noticias.model;


import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "not_noticias", schema = "public")
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "not_tx_nr_id")
    private Long notNrId;
    @Column(name = "not_tx_nome")
    private String notTxNome;
    @Column(name = "not_tx_descricao")
    private String notTxDescricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_categoria_nr_id",nullable = false)
    private Categoria categoria;

}
