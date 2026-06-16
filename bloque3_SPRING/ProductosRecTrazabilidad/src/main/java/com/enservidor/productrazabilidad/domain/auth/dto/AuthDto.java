package com.enservidor.productrazabilidad.domain.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AuthDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterRequest {
        @NotBlank(message = "El nombre de usuario es obligatorio")
        @Size(min = 4, max = 20, message = "El nombre de usuario debe tener entre 4 y 20 caracteres")
        private String username;
        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        private String password;
        @NotBlank(message = "El nombre es obligatorio")
        private String nombre;
        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El formato del email no es válido")
        private String email;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest {
        @NotBlank(message = "El nombre de usuario es obligatorio")
        private String username;
        @NotBlank(message = "La contraseña es obligatoria")
        private String password;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String token;
        private String type = "Bearer";
        private String username;
        public Response(String token, String username) {
            this.token = token;
            this.username = username;
        }
    }

}
