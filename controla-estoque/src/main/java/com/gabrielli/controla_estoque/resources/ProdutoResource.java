package com.gabrielli.controla_estoque.resources;

import com.gabrielli.controla_estoque.entity.Produto;
import com.gabrielli.controla_estoque.repository.ProdutoRepository;
import com.gabrielli.controla_estoque.services.ProdutoService;
import com.gabrielli.controla_estoque.types.TipoProduto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(value="/produto")
public class ProdutoResource {

    private ProdutoService produtoService;
    private ProdutoRepository produtoRepository;

    public ProdutoResource(ProdutoRepository produtoRepository, ProdutoService produtoService) {
        super();
        this.produtoRepository = produtoRepository;
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody Produto produto) {
        produtoService.save(produto);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        return new ResponseEntity<>(produtoService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path="/{codigo}")
    public ResponseEntity<Optional<Produto>> getById(@PathVariable Long codigo) {
        Optional<Produto> produto;
        try {
            produto = produtoService.getById(codigo);
            return  new ResponseEntity<Optional<Produto>>(produto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Optional<Produto>>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path="/{codigo}")
    public ResponseEntity<Optional<Produto>> deleteById(@PathVariable Long codigo) {
        try {
            produtoService.deleteById(codigo);
            return new ResponseEntity<Optional<Produto>>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Optional<Produto>>(HttpStatus.NOT_FOUND);
        }
    }

}
