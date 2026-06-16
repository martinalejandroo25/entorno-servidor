package com.enservidor.productrazabilidad.domain.lote.service;

import com.enservidor.productrazabilidad.domain.auth.Usuario;
import com.enservidor.productrazabilidad.domain.auth.repository.UsuarioRepository;
import com.enservidor.productrazabilidad.domain.lote.Lote;
import com.enservidor.productrazabilidad.domain.lote.dto.LoteDto;
import com.enservidor.productrazabilidad.domain.lote.mapper.LoteMapper;
import com.enservidor.productrazabilidad.domain.lote.repository.LoteRepository;
import com.enservidor.productrazabilidad.domain.producto.Producto;
import com.enservidor.productrazabilidad.domain.producto.repository.ProductoRepository;
import com.enservidor.productrazabilidad.exception.BadRequest;
import com.enservidor.productrazabilidad.exception.NotFound;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoteService {
    private final LoteRepository loteRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LoteMapper loteMapper;

    public LoteService(LoteRepository loteRepository,
                       ProductoRepository productoRepository,
                       UsuarioRepository usuarioRepository,
                       LoteMapper loteMapper) {
        this.loteRepository = loteRepository;
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
        this.loteMapper = loteMapper;
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
    public LoteDto.Response crearLote(Long productoId, LoteDto.Request request) {
        Usuario usuario = getUsuarioAutenticado();
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new NotFound("Producto no encontrado con id: " + productoId));
        if (!producto
                .getUsuario()
                .getId()
                .equals(usuario.getId())) {
            throw new BadRequest("No puedes agregar lotes a un producto de otro usuario");
        }

        if (loteRepository.existsByNumeroLoteAndProductoId(request.getNumeroLote(), productoId)) {
            throw new BadRequest("El numero de lote " +
                    request.getNumeroLote()+" ya existe para este producto");
        }

        Lote lote = loteMapper.toEntity(request);
        lote.setProducto(producto);

        return loteMapper.toResponse(loteRepository.save(lote));
    }

    public List<LoteDto.Response> listarLotesDeProducto(Long productoId) {
        Usuario usuario = getUsuarioAutenticado();
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new NotFound("Producto no encontrado con id: "+ productoId));

        if (!producto
                .getUsuario()
                .getId()
                .equals(usuario.getId())) {
            throw new BadRequest("Acceso no autorizado a los lotes de este producto");
        }

        return loteRepository.findByProductoId(productoId)
                .stream()
                .map(loteMapper::toResponse)
                .collect(Collectors.toList());
    }

    public LoteDto.Response obtenerPorId(Long id) {
        Usuario usuario = getUsuarioAutenticado();
        Lote lote = loteRepository.findById(id)
                .orElseThrow(() -> new NotFound("Lote no encontrado con id" + id));

        if(!lote
                .getProducto()
                .getUsuario()
                .getId()
                .equals(usuario.getId())) {
            throw new BadRequest("Acceso no autorizad a este lote:");
        }
        return loteMapper.toResponse(lote);
    }

    @Transactional
    public LoteDto.Response actualizarEstado(Long id, LoteDto.EstadoRequest request) {
        Usuario usuario = getUsuarioAutenticado();
        Lote lote = loteRepository.findById(id)
                .orElseThrow(() -> new NotFound("Lote no encontrado con id " + id));

        if (!lote
                .getProducto()
                .getUsuario()
                .getId()
                .equals(usuario.getId())) {
            throw new BadRequest("Acceso no autorizado para modificar este lote");
        }
        lote.setEstado(request.getEstado());
        return loteMapper.toResponse(loteRepository.save(lote));
    }
}
