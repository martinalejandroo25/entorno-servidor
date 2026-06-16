package com.enservidor.productrazabilidad.domain.auth;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario { //Tabla de usuario

    // identificador
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //nombre de usuario
    @Column(unique = true, nullable = false)
    private String username;

    // contraseña
    @Column(nullable = false)
    private String password;

    // nombre
    @Column(nullable = false)
    private String nombre;

    //email
    @Column(nullable = false)
    private String email;

    //Rol del usuario(USER o ADMIN)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
