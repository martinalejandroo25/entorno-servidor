package com.jaroso.primerproyecto.controllers;


import com.jaroso.primerproyecto.entities.Persona;
import com.jaroso.primerproyecto.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping("/hello")
    public String hello(){
        return "Hello Word";
    }

    @GetMapping("/personas")
    public ResponseEntity<List<Persona>> personas(){
        return ResponseEntity.ok(personaRepository.findAll());
    }

    @GetMapping("/addPersona")
       public String addPersona(){
            Persona p = new Persona();
            p.setNombre("Juanito");
            p.setApellidos("Jaroso");
            p. setEdad(25);
            personaRepository.save(p);
            return "Persona guardada";
       }
    }
