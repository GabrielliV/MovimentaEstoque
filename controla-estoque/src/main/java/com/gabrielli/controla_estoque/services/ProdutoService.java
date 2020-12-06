package com.gabrielli.controla_estoque.services;

import com.gabrielli.controla_estoque.entity.Produto;
import com.gabrielli.controla_estoque.types.TipoProduto;
import javassist.NotFoundException;

import java.util.List;

public interface ProdutoService {
    Produto save(Produto produto);

    List<Produto> getAll();

    Produto getById(Long codigo) throws NotFoundException;

    void deleteById(Long codigo);

    List<Produto> findByTipo(TipoProduto tipoProduto);
}
