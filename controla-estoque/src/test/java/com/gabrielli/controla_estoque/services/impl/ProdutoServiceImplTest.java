package com.gabrielli.controla_estoque.services.impl;

import com.gabrielli.controla_estoque.entity.Produto;
import com.gabrielli.controla_estoque.exception.ProdutoException;
import com.gabrielli.controla_estoque.repository.ProdutoRepository;
import com.gabrielli.controla_estoque.types.TipoMovimentacao;
import com.gabrielli.controla_estoque.types.TipoProduto;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProdutoServiceImplTest {

    @InjectMocks
    private ProdutoServiceImpl produtoService;
    @Mock
    private ProdutoRepository produtoRepository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveTest() {
        Produto produto = getProduto();

        Mockito.doReturn(produto).when(produtoRepository).save(produto);

        Produto produtoRet = produtoService.save(produto);
        assertThat(produtoRet.getCodigo(), equalTo(produto.getCodigo()));
        assertThat(produtoRet.getDescricao(), equalTo(produto.getDescricao()));
        assertThat(produtoRet.getTipo(), equalTo(produto.getTipo()));
        assertThat(produtoRet.getValor(), equalTo(produto.getValor()));
        assertThat(produtoRet.getQtdeEstoque(), equalTo(produto.getQtdeEstoque()));
    }

    @Test
    public void getAllTest() {
        Produto produto = new Produto();
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto);

        Mockito.doReturn(produtos).when(produtoRepository).findAll();

        List<Produto> produtosRet = produtoService.getAll();
        assertThat(produtosRet.get(0).getCodigo(), equalTo(produtos.get(0).getCodigo()));
        assertThat(produtosRet.get(0).getDescricao(), equalTo(produtos.get(0).getDescricao()));
        assertThat(produtosRet.get(0).getTipo(), equalTo(produtos.get(0).getTipo()));
        assertThat(produtosRet.get(0).getValor(), equalTo(produtos.get(0).getValor()));
        assertThat(produtosRet.get(0).getQtdeEstoque(), equalTo(produtos.get(0).getQtdeEstoque()));
    }

    @Test
    public void getByIdTest() {
        Produto produto = getProduto();

        Mockito.doReturn(Optional.of(produto)).when(produtoRepository).findById(produto.getCodigo());

        Produto produtoRet = produtoService.getById(produto.getCodigo());
        assertThat(produtoRet.getCodigo(), equalTo(produto.getCodigo()));
        assertThat(produtoRet.getDescricao(), equalTo(produto.getDescricao()));
        assertThat(produtoRet.getTipo(), equalTo(produto.getTipo()));
        assertThat(produtoRet.getValor(), equalTo(produto.getValor()));
        assertThat(produtoRet.getQtdeEstoque(), equalTo(produto.getQtdeEstoque()));
    }

    @Test(expected = ProdutoException.class)
    public void getByIdNotFoundTest() {
       produtoService.getById(1L);
    }

    @Test
    public void deleteByIdTest() {
        produtoService.deleteById(1L);
    }

    @Test
    public void findByTipoProdutoTeste() {
        Produto produto = new Produto();
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto);

        Mockito.doReturn(produtos).when(produtoRepository).findByTipo(TipoProduto.MOVEL);

        List<Produto> produtosRet = produtoService.findByTipo(TipoProduto.MOVEL);
        assertThat(produtosRet.get(0).getCodigo(), equalTo(produtos.get(0).getCodigo()));
        assertThat(produtosRet.get(0).getDescricao(), equalTo(produtos.get(0).getDescricao()));
        assertThat(produtosRet.get(0).getTipo(), equalTo(produtos.get(0).getTipo()));
        assertThat(produtosRet.get(0).getValor(), equalTo(produtos.get(0).getValor()));
        assertThat(produtosRet.get(0).getQtdeEstoque(), equalTo(produtos.get(0).getQtdeEstoque()));
    }

    private Produto getProduto() {
        Produto produto = new Produto();
        produto.setCodigo(1L);
        produto.setDescricao("Mesa");
        produto.setTipo(TipoProduto.MOVEL);
        produto.setValor(new BigDecimal(1560.20));
        produto.setQtdeEstoque(12);
        return produto;
    }
}
