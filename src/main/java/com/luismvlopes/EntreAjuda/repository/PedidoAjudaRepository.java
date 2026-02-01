package com.luismvlopes.EntreAjuda.repository;

import com.luismvlopes.EntreAjuda.model.entity.PedidoAjuda;
import com.luismvlopes.EntreAjuda.model.enums.Categoria;
import com.luismvlopes.EntreAjuda.model.enums.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoAjudaRepository extends JpaRepository<PedidoAjuda, Long> {

    List<PedidoAjuda> findByEstado(Estado estado);

    List<PedidoAjuda> findByCategoria(Categoria categoria);

    List<PedidoAjuda> findByZonaContainingIgnoreCase(String zona);
}
