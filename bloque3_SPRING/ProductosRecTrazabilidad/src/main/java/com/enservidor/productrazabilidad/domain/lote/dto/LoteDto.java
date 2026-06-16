package com.enservidor.productrazabilidad.domain.lote.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
public class LoteDto {

    @Getter
    @Setter
    public static class Request {
        @NotBlank(message = "El numero de lote es obligatorio")
        private String numeroLote;

        @NotNull(message = "La fecha de produccion es obligatoria")
        private LocalDate fechaProduccion;

        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 1, message = "La cantidad debe ser mayor que 0")
        private Integer cantidad;

        @NotBlank(message = "El estado es obligatorio")
        private String estado;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String numeroLote;
        private LocalDate fechaProduccion;
        private Integer cantidad;
        private String estado;
        private Long productoId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EstadoRequest {
        @NotBlank(message = "El estado no puede estar vacio")
        private String estado;
    }
}