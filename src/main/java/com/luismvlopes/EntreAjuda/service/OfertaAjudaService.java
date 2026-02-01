package com.luismvlopes.EntreAjuda.service;

import com.luismvlopes.EntreAjuda.exception.RecursoNaoEncontradoException;
import com.luismvlopes.EntreAjuda.model.dto.OfertaAjudaDTO;
import com.luismvlopes.EntreAjuda.model.entity.OfertaAjuda;
import com.luismvlopes.EntreAjuda.model.enums.Categoria;
import com.luismvlopes.EntreAjuda.model.enums.Estado;
import com.luismvlopes.EntreAjuda.repository.OfertaAjudaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfertaAjudaService {

    private final OfertaAjudaRepository repository;

    public OfertaAjudaService(OfertaAjudaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public OfertaAjudaDTO criar(OfertaAjudaDTO dto) {
        OfertaAjuda oferta = new OfertaAjuda();
        oferta.setTitulo(dto.getTitulo());
        oferta.setDescricao(dto.getDescricao());
        oferta.setCategoria(dto.getCategoria());
        oferta.setZona(dto.getZona());

        OfertaAjuda ofertaSalva = repository.save(oferta);
        return converterParaDTO(ofertaSalva);
    }

    @Transactional(readOnly = true)
    public List<OfertaAjudaDTO> listarTodas() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OfertaAjudaDTO buscarPorId(Long id) {
        OfertaAjuda oferta = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Oferta de ajuda não encontrada com id: " + id));
        return converterParaDTO(oferta);
    }

    @Transactional(readOnly = true)
    public List<OfertaAjudaDTO> listarPorEstado(Estado estado) {
        return repository.findByEstado(estado).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OfertaAjudaDTO> listarPorCategoria(Categoria categoria) {
        return repository.findByCategoria(categoria).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OfertaAjudaDTO> listarPorZona(String zona) {
        return repository.findByZonaContainingIgnoreCase(zona).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public OfertaAjudaDTO atualizarEstado(Long id, Estado novoEstado) {
        OfertaAjuda oferta = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Oferta de ajuda não encontrada com id: " + id));

        oferta.setEstado(novoEstado);
        OfertaAjuda ofertaAtualizada = repository.save(oferta);
        return converterParaDTO(ofertaAtualizada);
    }

    private OfertaAjudaDTO converterParaDTO(OfertaAjuda oferta) {
        return new OfertaAjudaDTO(
                oferta.getId(),
                oferta.getTitulo(),
                oferta.getDescricao(),
                oferta.getCategoria(),
                oferta.getZona(),
                oferta.getEstado(),
                oferta.getCriadoEm()
        );
    }
}
