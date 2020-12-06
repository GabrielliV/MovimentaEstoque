package com.gabrielli.controla_estoque.resources;

import com.gabrielli.controla_estoque.dto.EstoqueProdutoDTO;
import com.gabrielli.controla_estoque.entity.Estoque;
import com.gabrielli.controla_estoque.entity.Produto;
import com.gabrielli.controla_estoque.exception.EstoqueException;
import com.gabrielli.controla_estoque.exception.ProdutoException;
import com.gabrielli.controla_estoque.services.EstoqueService;
import com.gabrielli.controla_estoque.types.TipoProduto;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/estoque")
public class EstoqueResource {

    private final EstoqueService estoqueService;

    public EstoqueResource(EstoqueService estoqueService) {
        super();
        this.estoqueService = estoqueService;
    }

    @PostMapping
    public ResponseEntity<Estoque> save(@RequestBody Estoque estoque) {
        try {
            estoqueService.save(estoque);
            return new ResponseEntity<>(estoque, HttpStatus.OK);
        } catch (java.lang.Exception e) {
            throw new EstoqueException(e.getMessage());
        }
    }

    @GetMapping(path = "/tipo/{tipo}")
    public ResponseEntity<List<EstoqueProdutoDTO>> findByTipo(@PathVariable("tipo") TipoProduto tipoProduto) {
        return new ResponseEntity<>(estoqueService.getProdutoPorTipo(tipoProduto), HttpStatus.OK);
    }

    @GetMapping(path = "/lucro/{codigo}")
    public ResponseEntity<EstoqueProdutoDTO> findLucroProduto(@PathVariable("codigo") Produto produto) throws NotFoundException {
        EstoqueProdutoDTO estoqueProdutoDTO;
        try {
            estoqueProdutoDTO = estoqueService.getProdutoLucro(produto);
            return new ResponseEntity<>(estoqueProdutoDTO, HttpStatus.OK);
        } catch (java.lang.Exception e) {
            throw new ProdutoException(e.getMessage());
        }
    }

}
