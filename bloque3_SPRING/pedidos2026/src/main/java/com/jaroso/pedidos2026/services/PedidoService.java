package com.jaroso.pedidos2026.services;

import com.jaroso.pedidos2026.dtos.PedidoCreateDto;
import com.jaroso.pedidos2026.dtos.PedidoDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PedidoService {
    public PedidoDto create(PedidoCreateDto dto);
    public Optional<PedidoDto> findById(Long id);
    public List<PedidoDto> findAll();
    public boolean delete(Long id);
}
