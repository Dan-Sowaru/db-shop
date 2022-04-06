package com.example.dbshop.produto;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping()
    public ResponseEntity<Page<ProdutoEntity>> get(
//            @RequestParam(name = "fabricante") Long idFabricante
            @RequestParam(name = "offset", required = true) Integer offset,
            @RequestParam(name = "limit", required = true) Integer limit,
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "dataCriacao", required = false) String dataCriacao,
            @RequestParam(name = "fabricante", required = false) String fabricante,
            @RequestParam(name = "valor_maximo", required = false) BigDecimal valorMaximo

    ) {

         Page<ProdutoEntity> produtos = produtoService.buscarTodos(offset, limit, nome, valorMaximo);
        return ResponseEntity.ok(produtos);
    }


    @PostMapping
    public ResponseEntity<ProdutoEntity> create(@RequestBody ProdutoRequest request) {
    ProdutoEntity produto = produtoService.criar(request);
    return ResponseEntity.created(null).body(produto);
    }

    @GetMapping("/codigoBarra")
    public ResponseEntity<ProdutoEntity> getByCodigoBarra(
            @RequestParam(name = "codigoBarra") String codigoBarra
    ) {
        ProdutoEntity produto = produtoService.buscarPorCodigoBarra(codigoBarra);
        return ResponseEntity.ok(produto);
    }

}
