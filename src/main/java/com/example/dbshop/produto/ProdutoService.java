package com.example.dbshop.produto;

import com.example.dbshop.fabricante.FabricanteEntity;
import com.example.dbshop.fabricante.FabricanteRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

@AllArgsConstructor
@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;
    private FabricanteRepository fabricanteRepository;
    private EntityManager entityManager;


    public Page<ProdutoEntity> buscarTodos(Integer offset,
                                           Integer limit,
                                           String nome,
//                                         String fabricante,
                                           BigDecimal valor
    ) {
//


        Pageable pageable = new OffsetLimitPageable(offset, limit);
//        return produtoRepository.findAll(pageable);


//          FILTRO COM CRITERIA

//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<ProdutoEntity> query = criteriaBuilder.createQuery(ProdutoEntity.class);
//
////        Precisa informar o raiz do objeto
//        Root<ProdutoEntity> produtoEntityRoot = query.from(ProdutoEntity.class);
//
////        Criando a lista de predicados para múltiplos critérios
////        Método array
//
//        List<Predicate> predicates = new ArrayList<>();
//        if (nome != null) predicates.add(criteriaBuilder.like(produtoEntityRoot.get("nome"), "%"+nome+"%"));
//        if (valor != null) predicates.add(criteriaBuilder.lessThanOrEqualTo(produtoEntityRoot.get("valor"), valor));
//        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
//        return entityManager.createQuery(query).getResultList();
//
////        Método individuais
////        Predicate filtroNome = null;
////        Predicate filtroValor = null;
////        if (nome != null) filtroNome = (criteriaBuilder.like(produtoEntityRoot.get("nome"), nome));
////        if (valor != null) filtroValor = (criteriaBuilder.lessThanOrEqualTo(produtoEntityRoot.get("valor"), valor));
////        query.where(filtroNome, filtroValor);
////        return entityManager.createQuery(query).getResultList();
//
//
////        Predicate filtroOR = criteriaBuilder.or(
////                criteriaBuilder.equal(produtoEntityRoot.get("nome"), "Pablo"),
////                criteriaBuilder.equal(produtoEntityRoot.get("nome"), "Diogo")
////        );
//
//
////        if (nome != null)
////            query.where(criteriaBuilder.equal(produtoEntityRoot.get("nome"), nome));
////                  pode retornar um resultado único ou uma lista de resultados;
////            return entityManager.createQuery(query).getResultList();
////                  o criteriaBuilder permite vários predicates de predicates. Between, Like, Greater & equals etc.
////            Predicate filtroNome = criteriaBuilder.equal(produtoEntityRoot.get("nome"), nome);
////            query.where(filtroNome);
//    }
//
////      Exemplo Criteria
////        buscarTodos(String nomeProduto, String nomeFabricante, Integer offset, Integer limit)
////        List<FabricanteEntity> fabricantes = fabricanteRepository.findByName(nomeFabricante);
////        Criteria criteria = new CriteriaImpl();
////        criteria.add();//and fabricante in fabricantes
////        criteria.add();//and nome produto like nomeProduto;

//        FILTRO COM SPECIFICATION
        return produtoRepository.findAll(
                where(nomeContem(nome))
                        .and(valorMenorQue(valor)), pageable
        );
    }

    public ProdutoEntity buscarPorId(Long id) {
        return produtoRepository.findById(id).get(); //TODO adicionar tratamento de optional empty
    }

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

//    public ProdutoEntity buscarPorId(Long id) {
//        return produtoRepository.findById(id)
//                .orElseThrow(
//                        () -> new IllegalArgumentException("Produto não encontrado")
//                );
//
//    }

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


    // Specification
    static Specification<ProdutoEntity> nomeContem(String nome) {
        return nome == null ? null : (produtoEntity, cq, cb) -> cb.like(produtoEntity.get("nome"), "%" + nome + "%");
    }

    static Specification<ProdutoEntity> valorMenorQue(BigDecimal valor) {
        return valor == null ? null : (produtoEntity, cq, cb) -> cb.lessThanOrEqualTo(produtoEntity.get("valor"), valor);
    }

}
