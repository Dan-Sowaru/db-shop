package com.example.dbshop.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity,Long>{

    @Query(value = "SELECT * FROM produto WHERE id = ?1", nativeQuery = true)
    List<ProdutoEntity> findAllByIdFabricante(Long idFabricante);

    @Query(value = "SELECT * FROM produto WHERE codigo_barra = ?1", nativeQuery = true)
    ProdutoEntity findByCodigoBarra(String codigoBarra);




}
