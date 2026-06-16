package com.jaroso.pedidos2026.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "productos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    private Double precio;


}
