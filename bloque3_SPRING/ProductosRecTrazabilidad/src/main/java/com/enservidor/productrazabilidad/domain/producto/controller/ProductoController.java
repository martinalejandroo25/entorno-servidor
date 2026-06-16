package com.enservidor.productrazabilidad.domain.producto.controller;

import com.enservidor.productrazabilidad.domain.producto.Producto;
import com.enservidor.productrazabilidad.domain.producto.dto.ProductoDto;
import com.enservidor.productrazabilidad.domain.producto.service.ProductoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@AllArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoDto.Response> crearProducto(@Valid @RequestBody ProductoDto.Request request) {
        ProductoDto.Response response = productoService.crear(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ProductoDto.Response>> listarProductos() {
        List<ProductoDto.Response> productos = productoService.listarTodos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto.Response> obtenerProductoPorId(@PathVariable Long id) {
        ProductoDto.Response response = productoService.obtenerPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto.Response> actualizarProducto(@PathVariable Long id,
                                                                   @Valid @RequestBody ProductoDto.Request request) {
        ProductoDto.Response response = productoService.actualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.ok("Producto eliminado");
    }
}
