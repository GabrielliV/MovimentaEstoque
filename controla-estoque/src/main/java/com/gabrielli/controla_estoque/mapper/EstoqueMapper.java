package com.gabrielli.controla_estoque.mapper;

import com.gabrielli.controla_estoque.dto.EstoqueProdutoDTO;
import com.gabrielli.controla_estoque.entity.Produto;
import com.sun.istack.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class EstoqueMapper {
    public static EstoqueProdutoDTO toDTO(@NotNull final Produto produto) {

        return EstoqueProdutoDTO.builder()
                .codigo(produto.getCodigo())
                .descricao(produto.getDescricao())
                .tipo(produto.getTipo())
                .valor(produto.getValor())
                .qtdeEstoque(produto.getQtdeEstoque())
                .build();
    }

    public static List<EstoqueProdutoDTO> toDTOs(@NotNull final List<Produto> produtos) {
        return produtos.stream().map(EstoqueMapper::toDTO).collect(Collectors.toList());
    }
}
