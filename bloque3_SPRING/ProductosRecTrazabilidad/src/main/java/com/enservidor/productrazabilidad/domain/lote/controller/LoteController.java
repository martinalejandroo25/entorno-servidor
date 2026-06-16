package com.enservidor.productrazabilidad.domain.lote.controller;

import com.enservidor.productrazabilidad.domain.lote.dto.LoteDto;
import com.enservidor.productrazabilidad.domain.lote.service.LoteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class LoteController {
    private final LoteService loteService;

    @PostMapping("/productos/{productoId}/lotes")
    public ResponseEntity<LoteDto.Response> crearLote (
            @PathVariable Long productoId,
            @Valid @RequestBody LoteDto.Request request) {
        LoteDto.Response response = loteService.crearLote(productoId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/productos/{productoId}/lotes")
    public ResponseEntity<List<LoteDto.Response>> ListarLotesDeProducto(@PathVariable Long productoId) {
        List<LoteDto.Response> lotes = loteService.listarLotesDeProducto(productoId);
        return ResponseEntity.ok(lotes);
    }

    @GetMapping("/lotes/{id}")
    public ResponseEntity<LoteDto.Response> obtenerLotePorId(@PathVariable Long id) {
        LoteDto.Response response = loteService.obtenerPorId(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/lotes/{id}/estado")
    public ResponseEntity<LoteDto.Response> actualizarEstadoLote(
            @PathVariable Long id,
            @Valid @RequestBody LoteDto.EstadoRequest request) {
        LoteDto.Response response = loteService.actualizarEstado(id, request);
        return ResponseEntity.ok(response);
    }
}
