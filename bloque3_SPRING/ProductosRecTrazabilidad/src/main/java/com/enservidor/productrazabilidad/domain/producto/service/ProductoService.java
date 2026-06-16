package com.enservidor.productrazabilidad.domain.producto.service;

import com.enservidor.productrazabilidad.domain.auth.Usuario;
import com.enservidor.productrazabilidad.domain.auth.repository.UsuarioRepository;
import com.enservidor.productrazabilidad.domain.producto.Producto;
import com.enservidor.productrazabilidad.domain.producto.dto.ProductoDto;
import com.enservidor.productrazabilidad.domain.producto.mapper.ProductoMapper;
import com.enservidor.productrazabilidad.domain.producto.repository.ProductoRepository;
import com.enservidor.productrazabilidad.exception.BadRequest;
import com.enservidor.productrazabilidad.exception.NotFound;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoMapper productoMapper;

    public ProductoService(ProductoRepository productoRepository,
                           UsuarioRepository usuarioRepository,
                           ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoMapper = productoMapper;
    }

    private Usuario getUsuarioAutenticado(){
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new NotFound("Usuario no autenticado"));
    }

    @Transactional
    public ProductoDto.Response crear(ProductoDto.Request request){
        Usuario usuario = getUsuarioAutenticado();

        //codigo de producto unico por usuario
        if (productoRepository.existsByCodigoAndUsuarioId(request.getCodigo(),
                usuario.getId())) {
            throw new BadRequest("Ya tienes un producto registrado con el codigo: " + request.getCodigo());
        }

        Producto producto = productoMapper.toEntity(request);
        producto.setUsuario(usuario);

        return productoMapper.toResponse(productoRepository.save(producto));
    }

    public List<ProductoDto.Response> listarTodos() {
        Usuario usuario = getUsuarioAutenticado();
        return productoRepository.findByUsuarioId(usuario.getId())
                .stream()
                .map(productoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProductoDto.Response obtenerPorId(Long id) {
        Usuario usuario = getUsuarioAutenticado();
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotFound("Producto no encontrado con id " + id));
        if (!producto
                .getUsuario()
                .getId()
                .equals(usuario.getId())) {
            throw new BadRequest("Acceso no autorizado");
        }
        return productoMapper.toResponse(producto);
    }

    @Transactional
    public ProductoDto.Response actualizar(Long id, ProductoDto.Request request){
        Usuario usuario = getUsuarioAutenticado();
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotFound("Producto no encontrado con id " + id));
        if (!producto
                .getUsuario()
                .getId()
                .equals(usuario.getId())) {
            throw new BadRequest("Acceso no autorizado para actualizar este producto");
        }
        if (!producto.getCodigo().equals(request.getCodigo())
            && productoRepository.existsByCodigoAndUsuarioId(request.getCodigo(),
                usuario.getId())) {
            throw new BadRequest("El codigo" +
                    request.getCodigo() +"Ya esta registrado en otro de tus productos");
        }

        producto.setCodigo(request.getCodigo());
        producto.setNombre(request.getNombre());
        producto.setDescription(request.getDescripcion());

        return productoMapper.toResponse(productoRepository.save(producto));
    }

    @Transactional
    public void eliminar(Long id) {
        Usuario usuario = getUsuarioAutenticado();
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotFound("Producto no encontrado con id" + id));
    if(!producto
            .getUsuario()
            .getId()
            .equals(usuario.getId())){
        throw new BadRequest("Acceso no autorizado para eliminar este producto");
    }
    productoRepository.delete(producto);
    }
}
