package com.jaroso.pedidos2026.controllers;

import com.jaroso.pedidos2026.dtos.ProductoCreateDto;
import com.jaroso.pedidos2026.dtos.ProductoDto;
import com.jaroso.pedidos2026.services.ProductoService;
import com.jaroso.pedidos2026.services.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductoController {

    @Autowired
    private ProductoService productoservice;

    @GetMapping("/productos/{id}")
    public ResponseEntity<ProductoDto> getProducto(@PathVariable Long id) {
        Optional<ProductoDto> productoDto = productoservice.findById(id);
        return productoDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/productos")
    public ResponseEntity<List<ProductoDto>> getAllProductos(){
        return ResponseEntity.ok(productoservice.findAll());
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<ProductoDto> deleteProducto(@PathVariable Long id) {
        Optional<ProductoDto> productoDto = productoservice.findById(id);
        if (productoDto.isPresent()) {
            productoservice.delete(productoDto.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/productos")
    public ResponseEntity<ProductoDto> saveProducto(@RequestBody ProductoCreateDto productoCreateDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoservice.create(productoCreateDto));
    }


}