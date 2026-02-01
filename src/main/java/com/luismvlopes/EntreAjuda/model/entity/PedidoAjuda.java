package com.luismvlopes.EntreAjuda.model.entity;

import com.luismvlopes.EntreAjuda.model.enums.Categoria;
import com.luismvlopes.EntreAjuda.model.enums.Estado;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos_ajuda")
public class PedidoAjuda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(nullable = false, length = 1000)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    @Column(nullable = false, length = 100)
    private String zona;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
        if (estado == null) {
            estado = Estado.ABERTO;
        }
    }

    // Construtores
    public PedidoAjuda() {
    }

    public PedidoAjuda(String titulo, String descricao, Categoria categoria, String zona) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.zona = zona;
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