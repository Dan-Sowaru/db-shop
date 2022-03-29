package com.example.dbshop.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping()
    public ResponseEntity<List<ProdutoEntity>> get(

    ) {

        List<ProdutoEntity> produtos = produtoService.buscarTodos() ;
        return ResponseEntity.ok(produtos);

    }

    @PostMapping
    public ResponseEntity<ProdutoEntity> create(@RequestBody ProdutoRequest request) {
    ProdutoEntity produto = produtoService.criar(request);
    return ResponseEntity.created(null).body(produto);
    }
}
