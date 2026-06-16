package com.enservidor.productrazabilidad.domain.evento.service;

import com.enservidor.productrazabilidad.domain.auth.Usuario;
import com.enservidor.productrazabilidad.domain.auth.repository.UsuarioRepository;
import com.enservidor.productrazabilidad.domain.evento.EventoTrazabilidad;
import com.enservidor.productrazabilidad.domain.evento.dto.EventoDto;
import com.enservidor.productrazabilidad.domain.evento.mapper.EventoMapper;
import com.enservidor.productrazabilidad.domain.evento.repository.EventoRepository;
import com.enservidor.productrazabilidad.domain.lote.Lote;
import com.enservidor.productrazabilidad.domain.lote.repository.LoteRepository;
import com.enservidor.productrazabilidad.domain.producto.repository.ProductoRepository;
import com.enservidor.productrazabilidad.exception.BadRequest;
import com.enservidor.productrazabilidad.exception.NotFound;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoService {
    private final UsuarioRepository usuarioRepository;
    private final LoteRepository loteRepository;
    private final EventoRepository eventoRepository;
    private final EventoMapper eventoMapper;

    public EventoService(UsuarioRepository usuarioRepository,
                         LoteRepository loteRepository,
                         EventoRepository eventoRepository,
                         EventoMapper eventoMapper) {
        this.usuarioRepository = usuarioRepository;
        this.loteRepository = loteRepository;
        this.eventoRepository = eventoRepository;
        this.eventoMapper = eventoMapper;
    }

    private Usuario getUsuarioAutenticado() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new NotFound("Usuario no autenticado"));
    }

    @Transactional
    public EventoDto.Response registrarEvento(Long loteId, EventoDto.Request request) {
        Usuario usuario = getUsuarioAutenticado();
        Lote lote = loteRepository.findById(loteId)
                .orElseThrow(() -> new NotFound("Lote no encontrado con id:" + loteId));
        if (!lote.getProducto()
                .getUsuario()
                .getId().equals(usuario.getId())) {
            throw new BadRequest("No puedes agregar eventos a lotes de otros usuarios");
        }

        EventoTrazabilidad evento = eventoMapper.toEntity(request);
        evento.setLote(lote);

        //al registrar se actualiza el estado del lote
        lote.setEstado(request.getTipoEvento());
        loteRepository.save(lote);

        return eventoMapper.toResponse(eventoRepository.save(evento));
    }

    public List<EventoDto.Response> obtenerHistorialFiltrado(
            Long loteId,
            String tipo,
            LocalDateTime inicio,
            LocalDateTime fin) {
        Usuario usuario = getUsuarioAutenticado();
        Lote lote = loteRepository.findById(loteId)
                .orElseThrow(() -> new NotFound("Lote no encontrado con id: " + loteId));

        if(!lote.getProducto()
                .getUsuario()
                .getId()
                .equals(usuario.getId())) {
            throw new BadRequest("Acceso no autorizado al historial del lote");
        }

        List<EventoTrazabilidad> eventos = eventoRepository.findFilteredEvents(
                loteId, tipo, inicio, fin
        );
        return eventos.stream()
                .map(eventoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<String> obtenerRutaDeLote(Long loteId) {
        Usuario usuario = getUsuarioAutenticado();
        Lote lote = loteRepository.findById(loteId)
                .orElseThrow(() -> new NotFound("Lote no encontrado con id:" + loteId));

        if (!lote.getProducto()
                .getUsuario()
                .getId()
                .equals(usuario.getId())) {
            throw new BadRequest("Acceso no autorizado a la ruta del lote");
        }

        List<EventoTrazabilidad> eventos = eventoRepository.findFilteredEvents(
                loteId, null, null, null
        );
        return eventos.stream()
                .map(e -> String.format(
                        "[%s] Evento: %s en %s. Notas: %s",
                        e.getTimestamp(),
                        e.getTipoEvento(),
                        e.getUbicacion(),
                        e.getObservaciones() != null ?
                                e.getObservaciones()
                                : "Ninguna"))
                .collect(Collectors.toList());
    }
}
