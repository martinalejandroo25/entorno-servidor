package com.jaroso.pedidos2026.dtos;

import java.math.BigDecimal;

public record LineaPedidoDto(Long id, ProductoBasicoDto producto, Integer cantidad, BigDecimal subtotal) {
}
