package com.jaroso.pedidos2026.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "lineas_pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LineaPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    
    @Column(nullable = false)
    private Integer cantidad;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id")
    private Producto producto;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

}
