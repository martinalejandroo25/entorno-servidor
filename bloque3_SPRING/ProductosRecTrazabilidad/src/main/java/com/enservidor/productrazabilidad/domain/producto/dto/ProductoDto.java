package com.enservidor.productrazabilidad.domain.producto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//request
public class ProductoDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotBlank(message = "El código del producto es obligatorio")
        private String codigo;
        @NotBlank(message = "El nombre del producto es obligatorio")
        private String nombre;
        private String descripcion;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String codigo;
        private String nombre;
        private String descripcion;
        private Long usuarioId;
    }
}

