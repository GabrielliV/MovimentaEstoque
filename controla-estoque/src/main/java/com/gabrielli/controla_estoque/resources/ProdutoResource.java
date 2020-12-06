package com.gabrielli.controla_estoque.resources;

import com.gabrielli.controla_estoque.entity.Produto;
import com.gabrielli.controla_estoque.exception.ProdutoException;
import com.gabrielli.controla_estoque.services.ProdutoService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoResource {

    private ProdutoService produtoService;

    public ProdutoResource(ProdutoService produtoService) {
        super();
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

    @GetMapping(path = "/{codigo}")
    public ResponseEntity<Produto> getById(@PathVariable Long codigo) throws ProdutoException {
        Produto produto;
        try {
            produto = produtoService.getById(codigo);
            return new ResponseEntity<>(produto, HttpStatus.OK);
        } catch (NoSuchElementException | NotFoundException e) {
            throw new ProdutoException(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{codigo}")
    public ResponseEntity<Optional<Produto>> deleteById(@PathVariable Long codigo) throws ProdutoException {
        try {
            produtoService.deleteById(codigo);
            return new ResponseEntity<Optional<Produto>>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ProdutoException(e.getMessage());
        }
    }

}
