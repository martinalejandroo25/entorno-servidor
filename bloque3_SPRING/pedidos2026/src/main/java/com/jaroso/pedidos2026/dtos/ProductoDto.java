package com.jaroso.pedidos2026.dtos;

import java.math.BigDecimal;

public record ProductoDto(Long id, String nombre, BigDecimal precio, String descripcion) {
}
