package com.example.dbshop.produto;

import com.example.dbshop.fabricante.FabricanteEntity;
import lombok.*;
import org.aspectj.weaver.patterns.PerObject;

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
    private ProdutoStatus status;

    @JoinColumn(name = "ID_FABRICANTE")
    @ManyToOne(fetch = FetchType.LAZY)
    private FabricanteEntity idFabricante;

    @Column(name = "PESO")
    private Integer peso;

    @Column(name = "PESO_UNIDADE_MEDIDA")
    private String pesoUnidadeMedida;

    @Column(name = "DATA_CRIACAO")
    private ZonedDateTime dataCriacao;

    @Column(name = "DATA_ATUALIZACAO")
    private ZonedDateTime dataAtualizacao;


    public ProdutoEntity(String nome, String descricao, BigDecimal valor, String codigoBarra, FabricanteEntity idFabricante, Integer peso, String pesoUnidadeMedida) {
        this.codigo = UUID.randomUUID();
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.codigoBarra = codigoBarra;
        this.status = ProdutoStatus.ATIVO;
        this.idFabricante = idFabricante;
        this.peso = peso;
        this.pesoUnidadeMedida = pesoUnidadeMedida;
        this.dataCriacao = ZonedDateTime.now();
        this.dataAtualizacao = ZonedDateTime.now();
    }
}
