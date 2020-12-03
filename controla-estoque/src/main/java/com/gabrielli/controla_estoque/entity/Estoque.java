package com.gabrielli.controla_estoque.entity;

import com.gabrielli.controla_estoque.types.TipoMovimentacao;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
public class Estoque {
    @GeneratedValue
    @Id
    private Long codigo;
    private Long produto;
    private TipoMovimentacao tipoMovimentacao;
    private BigDecimal valorVenda;
    private OffsetDateTime dataVenda = OffsetDateTime.now();
    private int qtdeMovimentada;

    public Estoque() {

    }
}
