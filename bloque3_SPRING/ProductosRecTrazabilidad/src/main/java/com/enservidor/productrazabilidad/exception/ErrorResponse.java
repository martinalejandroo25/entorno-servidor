package com.enservidor.productrazabilidad.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) //ocultar nulos en el JSON response
public class ErrorResponse {

    private LocalDateTime timestamp;
    private String error;
    private String message;
    private Map<String, String> validaciones;

}
