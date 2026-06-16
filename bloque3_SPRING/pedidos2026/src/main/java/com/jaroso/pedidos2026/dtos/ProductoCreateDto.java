package com.jaroso.pedidos2026.dtos;

import java.math.BigDecimal;

public record ProductoCreateDto(String nombre, BigDecimal precio, String descripcion) {
}
