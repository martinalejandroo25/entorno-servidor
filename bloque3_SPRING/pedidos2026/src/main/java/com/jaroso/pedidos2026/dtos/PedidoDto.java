package com.jaroso.pedidos2026.dtos;

import com.jaroso.pedidos2026.entities.EstadoPedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record PedidoDto(Long id, EstadoPedido estado, LocalDate fecha, ClienteDto cliente,
                        List<LineaPedidoDto> lineasPedido, BigDecimal total) {
}
