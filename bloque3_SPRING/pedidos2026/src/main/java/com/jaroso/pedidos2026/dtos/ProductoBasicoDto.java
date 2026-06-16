package com.jaroso.pedidos2026.dtos;

import java.math.BigDecimal;

public record ProductoBasicoDto(Long id, String nombre, BigDecimal precio) {
}
