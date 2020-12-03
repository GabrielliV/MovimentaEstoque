package com.gabrielli.controla_estoque.repository;

import com.gabrielli.controla_estoque.entity.Produto;
import com.gabrielli.controla_estoque.types.TipoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByTipo(TipoProduto tipoProduto);

}
