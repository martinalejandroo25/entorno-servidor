package com.enservidor.productrazabilidad.domain.evento;

import com.enservidor.productrazabilidad.domain.lote.Lote;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "eventos_trazabilidad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoTrazabilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "tipo_evento", nullable = false)
    private String tipoEvento; //CREACION TRANSPORTE CONTRO_CALIDAD, ENTREGA

    @Column(nullable = false)
    private String ubicacion;

    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lote_id", nullable = false)
    private Lote lote;

    @PrePersist
    protected void onCreate(){
        if(this.timestamp == null) {
            this.timestamp = LocalDateTime.now();
        }
    }
}
