package com.jaroso.pedidos2026.dtos;

import java.util.List;

// Nos falta poner los pedidos
public record ClienteDto(Long id, String nombre, String email, List<PedidoResumenDto> pedidos) {
}
