package com.gabrielli.controla_estoque.services.impl;

import com.gabrielli.controla_estoque.dto.EstoqueProdutoDTO;
import com.gabrielli.controla_estoque.entity.Estoque;
import com.gabrielli.controla_estoque.entity.Produto;
import com.gabrielli.controla_estoque.exception.EstoqueException;
import com.gabrielli.controla_estoque.repository.EstoqueRepository;
import com.gabrielli.controla_estoque.types.TipoMovimentacao;
import com.gabrielli.controla_estoque.types.TipoProduto;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class EstoqueServiceImplTest {

    @InjectMocks
    private EstoqueServiceImpl estoqueService;
    @Mock
    private EstoqueRepository estoqueRepository;
    @Mock
    private ProdutoServiceImpl produtoService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveSaidaTest() throws Exception {
        Estoque estoque = getEstoque();
        Produto produto = getProduto();

        Mockito.doReturn(estoque).when(estoqueRepository).save(estoque);
        Mockito.doReturn(produto).when(produtoService).getById(1L);

        Estoque estoqueRet = estoqueService.save(estoque);
        assertThat(estoqueRet.getCodigo(), equalTo(estoque.getCodigo()));
        assertThat(estoqueRet.getProduto(), equalTo(estoque.getProduto()));
        assertThat(estoqueRet.getTipoMovimentacao(), equalTo(estoque.getTipoMovimentacao()));
        assertThat(estoqueRet.getValorVenda(), equalTo(estoque.getValorVenda()));
        assertThat(estoqueRet.getDataVenda(), equalTo(estoque.getDataVenda()));
        assertThat(estoqueRet.getQtdeMovimentada(), equalTo(estoque.getQtdeMovimentada()));
    }

    @Test
    public void saveEntradaTest() throws Exception {
        Estoque estoque = getEstoque();
        estoque.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
        Produto produto = getProduto();

        Mockito.doReturn(estoque).when(estoqueRepository).save(estoque);
        Mockito.doReturn(produto).when(produtoService).getById(1L);

        Estoque estoqueRet = estoqueService.save(estoque);
        assertThat(estoqueRet.getCodigo(), equalTo(estoque.getCodigo()));
        assertThat(estoqueRet.getProduto(), equalTo(estoque.getProduto()));
        assertThat(estoqueRet.getTipoMovimentacao(), equalTo(estoque.getTipoMovimentacao()));
        assertThat(estoqueRet.getValorVenda(), equalTo(estoque.getValorVenda()));
        assertThat(estoqueRet.getDataVenda(), equalTo(estoque.getDataVenda()));
        assertThat(estoqueRet.getQtdeMovimentada(), equalTo(estoque.getQtdeMovimentada()));
    }

    @Test(expected = EstoqueException.class)
    public void saveSaidaSemEstoqueTest() throws Exception {
        Estoque estoque = getEstoque();
        estoque.setQtdeMovimentada(11);
        Produto produto = getProduto();

        Mockito.doReturn(estoque).when(estoqueRepository).save(estoque);
        Mockito.doReturn(produto).when(produtoService).getById(1L);

        estoqueService.save(estoque);
    }

    @Test
    public void getProdutoPorTipoTest() {
        Produto produto = getProduto();
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto);

        Mockito.doReturn(produtos).when(produtoService).findByTipo(TipoProduto.MOVEL);

        List<EstoqueProdutoDTO> estoqueProdutoDTOS = estoqueService.getProdutoPorTipo(TipoProduto.MOVEL);
        assertThat(estoqueProdutoDTOS.get(0).getCodigo(), equalTo(produtos.get(0).getCodigo()));
        assertThat(estoqueProdutoDTOS.get(0).getDescricao(), equalTo(produtos.get(0).getDescricao()));
        assertThat(estoqueProdutoDTOS.get(0).getTipo(), equalTo(produtos.get(0).getTipo()));
        assertThat(estoqueProdutoDTOS.get(0).getValor(), equalTo(produtos.get(0).getValor()));
        assertThat(estoqueProdutoDTOS.get(0).getQtdeEstoque(), equalTo(produtos.get(0).getQtdeEstoque()));

    }

    @Test
    public void getProdutoLucroTest() throws NotFoundException {
        Produto produto = getProduto();
        Estoque estoque = getEstoque();
        List<Estoque> estoqueList = new ArrayList<>();
        estoqueList.add(estoque);

        Mockito.doReturn(produto).when(produtoService).getById(produto.getCodigo());
        Mockito.doReturn(1).when(estoqueRepository).getTotalSaida(produto.getCodigo());
        Mockito.doReturn(estoqueList).when(estoqueRepository).findByProdutoAndTipoMovimentacao(1L, TipoMovimentacao.SAIDA);

        EstoqueProdutoDTO estoqueProdutoDTOS = estoqueService.getProdutoLucro(produto);
        assertThat(estoqueProdutoDTOS.getCodigo(), equalTo(produto.getCodigo()));
        assertThat(estoqueProdutoDTOS.getDescricao(), equalTo(produto.getDescricao()));
        assertThat(estoqueProdutoDTOS.getTipo(), equalTo(produto.getTipo()));
        assertThat(estoqueProdutoDTOS.getValor(), equalTo(produto.getValor()));
        assertThat(estoqueProdutoDTOS.getQtdeEstoque(), equalTo(produto.getQtdeEstoque()));
        assertThat(estoqueProdutoDTOS.getQtdeSaida(), equalTo(1));
        assertThat(estoqueProdutoDTOS.getValorLucro(), equalTo(new BigDecimal(1305.00)));


    }

    private Produto getProduto() {
        Produto produto = new Produto();
        produto.setCodigo(1L);
        produto.setDescricao("Celular");
        produto.setTipo(TipoProduto.ELETRONICO);
        produto.setValor(new BigDecimal(1305.00));
        produto.setQtdeEstoque(10);
        return produto;
    }

    private Estoque getEstoque() {
        Estoque estoque = new Estoque();
        estoque.setCodigo(1L);
        estoque.setProduto(1L);
        estoque.setTipoMovimentacao(TipoMovimentacao.SAIDA);
        estoque.setValorVenda(new BigDecimal(2610.00));
        estoque.setDataVenda(OffsetDateTime.now());
        estoque.setQtdeMovimentada(1);
        return estoque;
    }

}
