package com.gabrielli.controla_estoque.repository;

import com.gabrielli.controla_estoque.entity.Estoque;
import com.gabrielli.controla_estoque.types.TipoMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    @Query("select sum(qtdeMovimentada) from Estoque where tipoMovimentacao = 1 and produto = :produto")
    Integer getTotalSaida(@Param("produto") Long produto);

    List<Estoque> findByProdutoAndTipoMovimentacao(Long produto, TipoMovimentacao tipoMovimentacao);
}
