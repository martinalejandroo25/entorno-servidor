package com.jaroso.pedidos2026.services;

import com.jaroso.pedidos2026.dtos.ClienteCreateDto;
import com.jaroso.pedidos2026.dtos.ClienteDto;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    public ClienteDto create(ClienteCreateDto dto);
    public List<ClienteDto> findAll();
    public Optional<ClienteDto> findById(Long id);
    public boolean delete(Long id);
}
