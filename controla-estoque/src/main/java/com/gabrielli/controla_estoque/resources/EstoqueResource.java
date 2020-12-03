package com.gabrielli.controla_estoque.resources;

import com.gabrielli.controla_estoque.dto.EstoqueProdutoDTO;
import com.gabrielli.controla_estoque.entity.Estoque;
import com.gabrielli.controla_estoque.entity.Produto;
import com.gabrielli.controla_estoque.repository.EstoqueRepository;
import com.gabrielli.controla_estoque.services.EstoqueService;
import com.gabrielli.controla_estoque.types.TipoProduto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/estoque")
public class EstoqueResource {

    private EstoqueService estoqueService;

    public EstoqueResource(EstoqueService estoqueService) {
        super();
        this.estoqueService = estoqueService;
    }

    @PostMapping
    public ResponseEntity<Estoque> save(@RequestBody Estoque estoque) throws Exception {
        try {
            estoqueService.save(estoque);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return new ResponseEntity<>(estoque, HttpStatus.OK);
    }

    @GetMapping(path="/tipo/{tipo}")
    public ResponseEntity<List<EstoqueProdutoDTO>> findByTipo(@PathVariable("tipo") TipoProduto tipoProduto) {
        return new ResponseEntity<>(estoqueService.getProdutoPorTipo(tipoProduto), HttpStatus.OK);
    }

    @GetMapping(path="/lucro/{codigo}")
    public ResponseEntity<EstoqueProdutoDTO> findLucroProduto(@PathVariable("codigo") Produto produto) {
        return new ResponseEntity<>(estoqueService.getProdutoLucro(produto), HttpStatus.OK);
    }

}
