package com.enservidor.productrazabilidad.domain.evento.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventoDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotBlank(message = "El tipo de evento es obligatorio")
        private String tipoEvento;

        @NotBlank(message = "La ubicacion es obligatoria")
        private String ubicacion;

        private String observaciones;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
      private Long id;
      private LocalDateTime timestamp;
      private String ubicacion;
      private String observaciones;
      private Long loteId;
    }
}
