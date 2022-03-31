package com.example.dbshop.fabricante;

import com.example.dbshop.produto.ProdutoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity(name= "FABRICANTE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FabricanteEntity {

//    Poderia ser
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fabricante_generator")
//    @SequenceGenerator(name="fabricante_generator", sequenceName = "fabricante_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DATA_CRIACAO")
    private ZonedDateTime dataCriacao;

    @Column(name = "DATA_ATUALIZACAO")
    private ZonedDateTime dataAtualizacao;

//    @OneToMany(fetch = CascadeType.LAZY, mappedBy = "fabricante")
//    private List<ProdutoEntity> produtos;




}
