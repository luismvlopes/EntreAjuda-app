package com.luismvlopes.EntreAjuda.service;

import com.luismvlopes.EntreAjuda.exception.RecursoNaoEncontradoException;
import com.luismvlopes.EntreAjuda.model.dto.PedidoAjudaDTO;
import com.luismvlopes.EntreAjuda.model.entity.PedidoAjuda;
import com.luismvlopes.EntreAjuda.model.enums.Categoria;
import com.luismvlopes.EntreAjuda.model.enums.Estado;
import com.luismvlopes.EntreAjuda.repository.PedidoAjudaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoAjudaService {

    private final PedidoAjudaRepository repository;

    public PedidoAjudaService(PedidoAjudaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public PedidoAjudaDTO criar(PedidoAjudaDTO dto) {
        PedidoAjuda pedido = new PedidoAjuda();
        pedido.setTitulo(dto.getTitulo());
        pedido.setDescricao(dto.getDescricao());
        pedido.setCategoria(dto.getCategoria());
        pedido.setZona(dto.getZona());

        PedidoAjuda pedidoSalvo = repository.save(pedido);
        return converterParaDTO(pedidoSalvo);
    }

    @Transactional(readOnly = true)
    public List<PedidoAjudaDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PedidoAjudaDTO buscarPorId(Long id) {
        PedidoAjuda pedido = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Pedido de ajuda não encontrado com id: " + id));
        return converterParaDTO(pedido);
    }

    @Transactional(readOnly = true)
    public List<PedidoAjudaDTO> listarPorEstado(Estado estado) {
        return repository.findByEstado(estado).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PedidoAjudaDTO> listarPorCategoria(Categoria categoria) {
        return repository.findByCategoria(categoria).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PedidoAjudaDTO> listarPorZona(String zona) {
        return repository.findByZonaContainingIgnoreCase(zona).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PedidoAjudaDTO atualizarEstado(Long id, Estado novoEstado) {
        PedidoAjuda pedido = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Pedido de ajuda não encontrado com id: " + id));

        pedido.setEstado(novoEstado);
        PedidoAjuda pedidoAtualizado = repository.save(pedido);
        return converterParaDTO(pedidoAtualizado);
    }

    private PedidoAjudaDTO converterParaDTO(PedidoAjuda pedido) {
        return new PedidoAjudaDTO(
                pedido.getId(),
                pedido.getTitulo(),
                pedido.getDescricao(),
                pedido.getCategoria(),
                pedido.getZona(),
                pedido.getEstado(),
                pedido.getCriadoEm()
        );
    }
}
