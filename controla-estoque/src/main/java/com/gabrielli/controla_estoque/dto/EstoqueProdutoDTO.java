package com.gabrielli.controla_estoque.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gabrielli.controla_estoque.types.TipoProduto;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class EstoqueProdutoDTO {
    private Long codigo;
    private String descricao;
    private TipoProduto tipo;
    private BigDecimal valor;
    private int qtdeEstoque;
    private int qtdeSaida;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal valorLucro;
}
