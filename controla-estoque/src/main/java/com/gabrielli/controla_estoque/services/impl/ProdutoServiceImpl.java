package com.gabrielli.controla_estoque.services.impl;

import com.gabrielli.controla_estoque.entity.Produto;
import com.gabrielli.controla_estoque.repository.ProdutoRepository;
import com.gabrielli.controla_estoque.services.ProdutoService;
import com.gabrielli.controla_estoque.types.TipoProduto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> getAll() {
        return produtoRepository.findAll();
    }

    @Override
    public Optional<Produto> getById(Long codigo) {
        Optional<Produto> produto;
        produto = produtoRepository.findById(codigo);
        return produto;
    }

    @Override
    public void deleteById(Long codigo) {
        produtoRepository.deleteById(codigo);
    }

    @Override
    public List<Produto> findByTipo(TipoProduto tipoProduto) {
        return produtoRepository.findByTipo(tipoProduto);
    }
}
