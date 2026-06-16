package com.enservidor.productrazabilidad.domain.evento.controller;

import com.enservidor.productrazabilidad.domain.evento.dto.EventoDto;
import com.enservidor.productrazabilidad.domain.evento.service.EventoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/lotes")
@AllArgsConstructor
public class EventoController {
    private final EventoService eventoService;

    @PostMapping("/{loteId}/eventos")
    public ResponseEntity<EventoDto.Response> registrarEvento(
            @PathVariable Long loteId,
            @Valid @RequestBody EventoDto.Request request) {
        EventoDto.Response response = eventoService.registrarEvento(loteId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{loteId}/eventos")
    public ResponseEntity<List<EventoDto.Response>> obtenerHistorialEventos(
            @PathVariable Long loteId,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin
    ) {
       List<EventoDto.Response> eventos = eventoService.obtenerHistorialFiltrado(
               loteId, tipo, inicio, fin
       );
       return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{loteId}/ruta")
    public ResponseEntity<List<String>> obtenerRutaDeLote(@PathVariable Long loteId) {
        List<String> ruta = eventoService.obtenerRutaDeLote(loteId);
        return ResponseEntity.ok(ruta);
    }
}
