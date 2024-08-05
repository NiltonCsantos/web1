package br.com.web1.noticias.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "not_noticias", schema = "public")
public class NoticiaEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "not_tx_nr_id")
    private Long notNrId;
    @Column(name = "not_tx_nome")
    private String notTxNome;
    @Column(name = "not_tx_descricao", columnDefinition = "TEXT")
    private String notTxDescricao;
    @Column(name = "not_tx_url")
    private String notTxUrl;
    @Column(name = "not_tx_imagem")
    String notTxImagem;
    @Column(name = "not_dt_publicacao")
    private LocalDateTime notDtPublicao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_categoria_nr_id",nullable = false)
    private CategoriaEntidade categoria;

}
