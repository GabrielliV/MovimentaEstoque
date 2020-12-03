package com.gabrielli.controla_estoque.services;

import com.gabrielli.controla_estoque.entity.Produto;
import com.gabrielli.controla_estoque.types.TipoProduto;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {
    Produto save(Produto produto);
    List<Produto> getAll();
    Optional<Produto> getById(Long codigo);
    void deleteById(Long codigo);
    List<Produto> findByTipo(TipoProduto tipoProduto);
}
