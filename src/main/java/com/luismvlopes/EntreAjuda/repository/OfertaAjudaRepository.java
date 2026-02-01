package com.luismvlopes.EntreAjuda.repository;

import com.luismvlopes.EntreAjuda.model.entity.OfertaAjuda;
import com.luismvlopes.EntreAjuda.model.enums.Categoria;
import com.luismvlopes.EntreAjuda.model.enums.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OfertaAjudaRepository extends JpaRepository<OfertaAjuda, Long> {

    List<OfertaAjuda> findByEstado(Estado estado);

    List<OfertaAjuda> findByCategoria(Categoria categoria);

    List<OfertaAjuda> findByZonaContainingIgnoreCase(String zona);
}