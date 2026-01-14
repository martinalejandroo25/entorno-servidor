package com.jaroso.primerproyecto.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincremental
    private Integer id;
    private String nombre;
    private String apellidos;
    private Integer edad;
}
