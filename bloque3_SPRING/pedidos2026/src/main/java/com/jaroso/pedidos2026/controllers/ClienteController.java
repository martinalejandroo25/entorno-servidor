package com.jaroso.pedidos2026.controllers;

import com.jaroso.pedidos2026.dtos.ClienteCreateDto;
import com.jaroso.pedidos2026.dtos.ClienteDto;
import com.jaroso.pedidos2026.entities.Cliente;
import com.jaroso.pedidos2026.repositories.ClienteRepository;
import com.jaroso.pedidos2026.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getAllClientes(){
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getCliente(@PathVariable Long id){
        return clienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClienteDto> saveCliente(@RequestBody ClienteCreateDto cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.create(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable Long id){
        boolean encontrado = clienteService.delete(id);
        if (encontrado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}