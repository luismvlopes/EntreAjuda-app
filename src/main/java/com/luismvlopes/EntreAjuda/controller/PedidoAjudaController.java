package com.luismvlopes.EntreAjuda.controller;

import com.luismvlopes.EntreAjuda.model.dto.PedidoAjudaDTO;
import com.luismvlopes.EntreAjuda.model.enums.Categoria;
import com.luismvlopes.EntreAjuda.model.enums.Estado;
import com.luismvlopes.EntreAjuda.service.PedidoAjudaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoAjudaController {

    private final PedidoAjudaService service;

    public PedidoAjudaController(PedidoAjudaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PedidoAjudaDTO> criar(@Valid @RequestBody PedidoAjudaDTO dto) {
        PedidoAjudaDTO pedidoCriado = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoCriado);
    }

    @GetMapping
    public ResponseEntity<List<PedidoAjudaDTO>> listarTodos(
            @RequestParam(required = false) Estado estado,
            @RequestParam(required = false) Categoria categoria,
            @RequestParam(required = false) String zona) {

        List<PedidoAjudaDTO> pedidos;

        if (estado != null) {
            pedidos = service.listarPorEstado(estado);
        } else if (categoria != null) {
            pedidos = service.listarPorCategoria(categoria);
        } else if (zona != null && !zona.isBlank()) {
            pedidos = service.listarPorZona(zona);
        } else {
            pedidos = service.listarTodos();
        }

        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoAjudaDTO> buscarPorId(@PathVariable Long id) {
        PedidoAjudaDTO pedido = service.buscarPorId(id);
        return ResponseEntity.ok(pedido);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<PedidoAjudaDTO> atualizarEstado(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        Estado novoEstado = Estado.valueOf(body.get("estado"));
        PedidoAjudaDTO pedidoAtualizado = service.atualizarEstado(id, novoEstado);
        return ResponseEntity.ok(pedidoAtualizado);
    }
}
