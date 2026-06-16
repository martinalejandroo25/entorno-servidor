package com.enservidor.productrazabilidad.domain.producto;

import com.enservidor.productrazabilidad.domain.auth.Usuario;
import com.enservidor.productrazabilidad.domain.lote.Lote;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
    @Table(
            name = "productos",
            uniqueConstraints = @UniqueConstraint(columnNames = {"codigo", "usuario_id"})
    )
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class Producto {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String codigo;

        @Column(nullable = false)
        private String nombre;

        private String description;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "usuario_id", nullable = false)
        private Usuario usuario;

        @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
        @Builder.Default
        private List<Lote> lotes = new ArrayList<>();
    }
