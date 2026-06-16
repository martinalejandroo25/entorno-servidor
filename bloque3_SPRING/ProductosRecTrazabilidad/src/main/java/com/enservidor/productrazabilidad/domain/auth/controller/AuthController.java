package com.enservidor.productrazabilidad.domain.auth.controller;

import com.enservidor.productrazabilidad.domain.auth.dto.AuthDto;
import com.enservidor.productrazabilidad.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registrarUsuario(@Valid @RequestBody AuthDto.RegisterRequest request) {
        String mensaje = authService.registrar(request);

        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDto.Response> login(@Valid @RequestBody AuthDto.LoginRequest request) {
        AuthDto.Response response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
