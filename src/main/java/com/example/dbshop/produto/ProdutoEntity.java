package com.example.dbshop.produto;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@Entity
@Table(name="PRODUTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sq_produto")
    @Setter(AccessLevel.NONE)

    private Long id;
    @Column(name = "CODIGO")
    private UUID codigo;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Column(name = "CODIGO_BARRA")
    private String codigoBarra;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "ID_FABRICANTE")
    private Long idFabricante;
    @Column(name = "PESO")
    private Integer peso;
    @Column(name = "PESO_UNIDADE_MEDIDA")
    private String pesoUnidadeMedida;
    @Column(name = "DATA_CRIACAO")
    private ZonedDateTime dataCriacao;
    @Column(name = "DATA_ATUALIZACAO")
    private ZonedDateTime dataAtualizacao;

    public ProdutoEntity(String nome, String descricao, BigDecimal valor, String codigoBarra, Long idFabricante, Integer peso, String pesoUnidadeMedida) {
        this.codigo = UUID.randomUUID();
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.codigoBarra = codigoBarra;
        this.status = "ATIVO";
        this.idFabricante = idFabricante;
        this.peso = peso;
        this.pesoUnidadeMedida = pesoUnidadeMedida;
        this.dataCriacao = ZonedDateTime.now();
        this.dataAtualizacao = ZonedDateTime.now();
    }
}
