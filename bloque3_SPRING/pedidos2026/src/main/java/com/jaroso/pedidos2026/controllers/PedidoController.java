package com.jaroso.pedidos2026.controllers;

import com.jaroso.pedidos2026.dtos.PedidoCreateDto;
import com.jaroso.pedidos2026.dtos.PedidoDto;
import com.jaroso.pedidos2026.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDto> savePedido(@RequestBody PedidoCreateDto pedido){
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.create(pedido));
    }

    @GetMapping
    public ResponseEntity<List<PedidoDto>> findAll(){
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> getPedido(@PathVariable Long id){
        return pedidoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePedido(@PathVariable Long id){
        boolean encontrado = pedidoService.delete(id);
        if(encontrado){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}