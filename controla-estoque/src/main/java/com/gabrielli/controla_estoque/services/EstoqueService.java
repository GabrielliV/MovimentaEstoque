package com.gabrielli.controla_estoque.services;

import com.gabrielli.controla_estoque.dto.EstoqueProdutoDTO;
import com.gabrielli.controla_estoque.entity.Estoque;
import com.gabrielli.controla_estoque.entity.Produto;
import com.gabrielli.controla_estoque.types.TipoProduto;

import java.util.List;

public interface EstoqueService {
    Estoque save(Estoque estoque) throws Exception;
    List<EstoqueProdutoDTO> getProdutoPorTipo(TipoProduto tipoProduto);
    EstoqueProdutoDTO getProdutoLucro(Produto produto);
}
