package com.enservidor.productrazabilidad.domain.evento.repository;

import com.enservidor.productrazabilidad.domain.evento.EventoTrazabilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EventoRepository extends JpaRepository<EventoTrazabilidad, Long> {

    @Query(
            "select e from EventoTrazabilidad e where e.lote.id = :loteId " +
                "and (:tipo is null or e.tipoEvento = :tipo) " +
                "and (:fechaInicio is null or e.timestamp >= :fechaInicio)" +
                "and (:fechaFin is null or e.timestamp <= :fechaFin)" +
                "order by e.timestamp asc"
    )
    List<EventoTrazabilidad> findFilteredEvents(
            @Param("loteId") Long loteId,
            @Param("tipo") String tipo,
            @Param("fechaInicio")LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin
            );
}