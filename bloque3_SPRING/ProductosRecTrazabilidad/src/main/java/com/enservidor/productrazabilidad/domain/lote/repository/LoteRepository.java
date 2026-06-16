package com.enservidor.productrazabilidad.domain.lote.repository;

import com.enservidor.productrazabilidad.domain.lote.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Long> {

    //Listar lotes que pertenecen a un producto
    List<Lote> findByProductoId(Long productoId);

    //buscar por lote y producto para comprobar que sea unico
    Optional<Lote> findByNumeroLoteAndProductoId(String numeroLote, Long productoId);

    //Verificar si existe el lote para este producto
    boolean existsByNumeroLoteAndProductoId(String numeroLote, Long ProductoId);
}
