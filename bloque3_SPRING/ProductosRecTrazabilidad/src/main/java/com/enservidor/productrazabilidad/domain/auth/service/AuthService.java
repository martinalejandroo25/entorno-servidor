package com.enservidor.productrazabilidad.domain.auth.service;

import com.enservidor.productrazabilidad.domain.auth.Role;
import com.enservidor.productrazabilidad.domain.auth.Usuario;
import com.enservidor.productrazabilidad.domain.auth.dto.AuthDto;
import com.enservidor.productrazabilidad.domain.auth.repository.UsuarioRepository;
import com.enservidor.productrazabilidad.exception.BadRequest;
import com.enservidor.productrazabilidad.security.util.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtTokenProvider jwtTokenProvider) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String registrar(AuthDto.RegisterRequest request) {
        //usuario unico
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new BadRequest(
                    "El nombre de usuario " +
                    request.getUsername()+
                    " ya esta en uso");
        }
        //crear Usuario con la pass encriptada
        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .nombre(request.getNombre())
                .email(request.getEmail())
                .role(Role.ROLE_USER)
                .build();
        usuarioRepository.save(usuario);
        return "usuario registrado con exito";
    }
    public AuthDto.Response login(AuthDto.LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        //guardar sesion en el contexto de Spring Security
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //generar token jwt
        String token = jwtTokenProvider.generateToken(authentication);

        return new AuthDto.Response(token, request.getUsername());

    }
}
