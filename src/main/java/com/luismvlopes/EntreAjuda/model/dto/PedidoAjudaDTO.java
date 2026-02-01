package com.luismvlopes.EntreAjuda.model.dto;

import com.luismvlopes.EntreAjuda.model.enums.Categoria;
import com.luismvlopes.EntreAjuda.model.enums.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class PedidoAjudaDTO {

    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 200, message = "O título não pode exceder 200 caracteres")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 1000, message = "A descrição não pode exceder 1000 caracteres")
    private String descricao;

    @NotNull(message = "A categoria é obrigatória")
    private Categoria categoria;

    @NotBlank(message = "A zona é obrigatória")
    @Size(max = 100, message = "A zona não pode exceder 100 caracteres")
    private String zona;

    private Estado estado;

    private LocalDateTime criadoEm;

    // Construtores
    public PedidoAjudaDTO() {}

    public PedidoAjudaDTO(Long id, String titulo, String descricao, Categoria categoria,
                          String zona, Estado estado, LocalDateTime criadoEm) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.zona = zona;
        this.estado = estado;
        this.criadoEm = criadoEm;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}
