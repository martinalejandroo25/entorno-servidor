package com.jaroso.pedidos2026.dtos;

import java.util.List;

public record PedidoCreateDto(Long clienteId, List<LineaPedidoCreateDto> lineas) {

}
