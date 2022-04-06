package com.example.dbshop.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity,Long>{

//    @Query(value = "SELECT * FROM produto WHERE id = ?1", nativeQuery = true)
//    List<ProdutoEntity> findAllByIdFabricante(Long idFabricante);


//    Query Nativa
    @Query(value = "SELECT * FROM produto WHERE codigo_barra = ?1", nativeQuery = true)
    ProdutoEntity findByCodigoBarra(String codigoBarra);

    List<ProdutoEntity> findAllByIdFabricante(Long idFabricante);


//    Query JPQL
//    @Query("SELECT p from ProdutoEntity p where p.codigoBarra = :codigoBarra")
//    ProdutoEntity findByCodigoBarra(String codigoBarra);


//    @RequestParam(name = "nome", required = false) String nome,
//    @RequestParam(name = "dataCriacao", required = false) String dataCriacao,
//    @RequestParam(name = "fabricante", required = false) String fabricante,
//    @RequestParam(name = "valorMaximo", required = false) String valorMaximo

}
