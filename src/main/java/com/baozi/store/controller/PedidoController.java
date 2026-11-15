package com.baozi.store.controller;

import com.baozi.store.model.Pedido;
import com.baozi.store.repository.PedidoRepository;
import com.baozi.store.repository.ClienteRepository;
import com.baozi.store.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository repository;

    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoController(PedidoRepository repository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    // POST - criar
    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody Pedido pedido) {
        pedido.setId(null);
        validar(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(pedido));
    }

    // GET /{id} - consultar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET - listar todos
    @GetMapping
    public List<Pedido> listarTodos() {
        return repository.findAll();
    }

    // PUT /{id} - atualizar (opcional)
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pedido.setId(id);
        validar(pedido);
        return ResponseEntity.ok(repository.save(pedido));
    }

    // DELETE /{id} - apagar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Regra simples: cliente e produto precisam existir e a quantidade deve ser > 0
    private void validar(Pedido pedido) {
        if (pedido.getClienteId() == null || !clienteRepository.existsById(pedido.getClienteId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado");
        }
        if (pedido.getProdutoId() == null || !produtoRepository.existsById(pedido.getProdutoId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado");
        }
        if (pedido.getQuantidade() == null || pedido.getQuantidade() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade deve ser maior que zero");
        }
    }
}
