package com.gabrielli.controla_estoque.entity;

import com.gabrielli.controla_estoque.types.TipoProduto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Produto {
    @GeneratedValue
    @Id
    private Long codigo;
    private String descricao;
    private TipoProduto tipo;
    private BigDecimal valor;
    private int qtdeEstoque;

    public Produto() {
    }

    public Produto(String descricao, TipoProduto tipo, BigDecimal valor, int qtdeEstoque) {
        super();
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
        this.qtdeEstoque = qtdeEstoque;
    }
}
