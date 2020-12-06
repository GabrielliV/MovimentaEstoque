package com.gabrielli.controla_estoque.services.impl;

import com.gabrielli.controla_estoque.dto.EstoqueProdutoDTO;
import com.gabrielli.controla_estoque.entity.Estoque;
import com.gabrielli.controla_estoque.entity.Produto;
import com.gabrielli.controla_estoque.exception.EstoqueException;
import com.gabrielli.controla_estoque.mapper.EstoqueMapper;
import com.gabrielli.controla_estoque.repository.EstoqueRepository;
import com.gabrielli.controla_estoque.services.EstoqueService;
import com.gabrielli.controla_estoque.services.ProdutoService;
import com.gabrielli.controla_estoque.types.TipoMovimentacao;
import com.gabrielli.controla_estoque.types.TipoProduto;
import com.sun.istack.NotNull;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EstoqueServiceImpl implements EstoqueService {
    @Autowired
    private EstoqueRepository estoqueRepository;
    private ProdutoService produtoService;

    @Override
    public Estoque save(Estoque estoque) throws Exception {
        atualizaEstoque(estoque);
        return estoqueRepository.save(estoque);
    }

    private void atualizaEstoque(Estoque estoque) throws Exception {
        Produto produto = produtoService.getById(estoque.getProduto());
        int saldo;
        int qtdeEstoque = produto.getQtdeEstoque();
        int qtdeMovimentacao = estoque.getQtdeMovimentada();
        if (estoque.getTipoMovimentacao().equals(TipoMovimentacao.ENTRADA)) {
            saldo = qtdeEstoque + qtdeMovimentacao;
        } else {
            if (qtdeEstoque >= qtdeMovimentacao) {
                saldo = qtdeEstoque - qtdeMovimentacao;
            } else {
                throw new EstoqueException("Quantidade em estoque insuficiente.");
            }
        }
        produto.setQtdeEstoque(saldo);
    }

    private EstoqueProdutoDTO setTotalSaida(EstoqueProdutoDTO estoqueProdutoDTO) {
        Integer qtdeSaida = estoqueRepository.getTotalSaida(estoqueProdutoDTO.getCodigo());
        estoqueProdutoDTO.setQtdeSaida(qtdeSaida != null ? qtdeSaida : 0);
        return estoqueProdutoDTO;
    }

    private List<EstoqueProdutoDTO> setTotalSaida(@NotNull final List<EstoqueProdutoDTO> produtosEstoqueProdutoDTOS) {
        return produtosEstoqueProdutoDTOS.stream().map(this::setTotalSaida).collect(Collectors.toList());
    }

    @Override
    public List<EstoqueProdutoDTO> getProdutoPorTipo(TipoProduto tipoProduto) {
        List<Produto> produtos = produtoService.findByTipo(tipoProduto);
        List<EstoqueProdutoDTO> estoqueProdutoDTO = EstoqueMapper.toDTOs(produtos);
        return setTotalSaida(estoqueProdutoDTO);
    }

    @Override
    public EstoqueProdutoDTO getProdutoLucro(Produto produto) throws NotFoundException {
        Produto produtoMapper = produtoService.getById(produto.getCodigo());
        EstoqueProdutoDTO estoqueProdutoDTO = EstoqueMapper.toDTO(produtoMapper);
        setTotalSaida(estoqueProdutoDTO);
        return setTotalLucro(estoqueProdutoDTO);
    }

    private EstoqueProdutoDTO setTotalLucro(EstoqueProdutoDTO estoqueProdutoDTO) {
        List<Estoque> estoqueList = estoqueRepository.findByProdutoAndTipoMovimentacao(estoqueProdutoDTO.getCodigo(), TipoMovimentacao.SAIDA);
        estoqueProdutoDTO.setValorLucro(BigDecimal.ZERO);
        for (Estoque estoque : estoqueList) {
            BigDecimal lucro = (estoque.getValorVenda().subtract(estoqueProdutoDTO.getValor())
                    .multiply(new BigDecimal(estoque.getQtdeMovimentada())));
            estoqueProdutoDTO.setValorLucro(estoqueProdutoDTO.getValorLucro().add(lucro));
        }
        return estoqueProdutoDTO;
    }

}
