package com.example.dbshop.produto;

import com.example.dbshop.fabricante.FabricanteEntity;
import com.example.dbshop.fabricante.FabricanteRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;
    private FabricanteRepository fabricanteRepository;



    public Page<ProdutoEntity> buscarTodos(Integer offset, Integer limit) {

//        no Raphael está Pageable, comigo só funcionou OffsetLimitPageable
        Pageable pageable = new OffsetLimitPageable(offset, limit);

        return produtoRepository.findAll(pageable);
    }

//    public ProdutoEntity buscarPorId(Long id) {
//        return produtoRepository.findById(id).get(); //TODO adicionar tratamento de optional empty
//    }

    public List<ProdutoEntity> buscarTodosDeUmFabricante(Long idFabricante) {
//        if (idFabricante == null)
            return produtoRepository.findAllByIdFabricante(idFabricante);
//        return produtoRepository.findAll();

    }

    public ProdutoEntity buscarPorCodigoBarra(String codigoBarra) {
        return produtoRepository.findByCodigoBarra(codigoBarra);

    }

    public ProdutoEntity criar(ProdutoRequest produtoRequest) {

//        Optional<Fabricante> fabricante fabricanteService.buscarFabricantePorId(produtoRequest.getFabricanteId());
//        if (!fabricante.isPresent()) {
//            throw new IllegalArgumentException("Fabricante não encontrado");
//        }

        Optional<FabricanteEntity> fabricanteEntity = fabricanteRepository.findById(produtoRequest.getIdFabricante());
//        TODO Implementar exception para o sistema;
        ProdutoEntity produtoEntity = toEntity(produtoRequest, fabricanteEntity.get());
        return produtoRepository.save(produtoEntity);
    }



    public ProdutoEntity atualizar(Long id, ProdutoRequest produtoRequest) {

        Optional<ProdutoEntity> optProdutoEntity = produtoRepository.findById(id);
//        Valido se recuperou ou 404
        ProdutoEntity produtoEntity = optProdutoEntity.get();
        produtoEntity.setValor(produtoRequest.getValor());
        return produtoRepository.save(produtoEntity);
    }

    public ProdutoEntity buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Produto não encontrado")
                );

    }

    public ProdutoEntity toEntity(ProdutoRequest produtoRequest, FabricanteEntity fabricanteEntity) {
        return new ProdutoEntity(
                produtoRequest.getNome(),
                produtoRequest.getDescricao(),
                produtoRequest.getValor(),
                produtoRequest.getCodigoBarra(),
                fabricanteEntity,
                produtoRequest.getPeso(),
                produtoRequest.getPesoUnidadeMedida());
    }

}
