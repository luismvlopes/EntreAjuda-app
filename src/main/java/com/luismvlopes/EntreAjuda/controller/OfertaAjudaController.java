package com.luismvlopes.EntreAjuda.controller;

import com.luismvlopes.EntreAjuda.model.dto.OfertaAjudaDTO;
import com.luismvlopes.EntreAjuda.model.enums.Categoria;
import com.luismvlopes.EntreAjuda.model.enums.Estado;
import com.luismvlopes.EntreAjuda.service.OfertaAjudaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ofertas")
@CrossOrigin(origins = "*")
public class OfertaAjudaController {

    private final OfertaAjudaService service;

    public OfertaAjudaController(OfertaAjudaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OfertaAjudaDTO> criar(@Valid @RequestBody OfertaAjudaDTO dto) {
        OfertaAjudaDTO ofertaCriada = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ofertaCriada);
    }

    @GetMapping
    public ResponseEntity<List<OfertaAjudaDTO>> listarTodas(
            @RequestParam(required = false) Estado estado,
            @RequestParam(required = false) Categoria categoria,
            @RequestParam(required = false) String zona) {

        List<OfertaAjudaDTO> ofertas;

        if (estado != null) {
            ofertas = service.listarPorEstado(estado);
        } else if (categoria != null) {
            ofertas = service.listarPorCategoria(categoria);
        } else if (zona != null && !zona.isBlank()) {
            ofertas = service.listarPorZona(zona);
        } else {
            ofertas = service.listarTodas();
        }

        return ResponseEntity.ok(ofertas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfertaAjudaDTO> buscarPorId(@PathVariable Long id) {
        OfertaAjudaDTO oferta = service.buscarPorId(id);
        return ResponseEntity.ok(oferta);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<OfertaAjudaDTO> atualizarEstado(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        Estado novoEstado = Estado.valueOf(body.get("estado"));
        OfertaAjudaDTO ofertaAtualizada = service.atualizarEstado(id, novoEstado);
        return ResponseEntity.ok(ofertaAtualizada);
    }
}
