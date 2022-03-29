package com.example.dbshop.produto;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProdutoService {
    private ProdutoRepository produtoRepository;



    public List<ProdutoEntity> buscarTodos() {
        return produtoRepository.findAll();
    }

    public ProdutoEntity criar(ProdutoRequest produtoRequest) {

//        Optional<Fabricante> fabricante fabricanteService.buscarFabricantePorId(produtoRequest.getFabricanteId());
//        if (!fabricante.isPresent()) {
//            throw new IllegalArgumentException("Fabricante não encontrado");
//        }

        ProdutoEntity produtoEntity = produtoRequest.toEntity();
        return produtoRepository.save(produtoEntity);
    }

    public ProdutoEntity buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Produto não encontrado")
                );

    }

}
