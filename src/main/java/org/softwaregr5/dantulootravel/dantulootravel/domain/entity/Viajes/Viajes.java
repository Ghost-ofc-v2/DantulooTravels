package org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios.Conductor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Data
@Entity
@Table(name = "viajes")
public class Viajes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_viajes;

    private String ciudad;
    @Column(name = "fecha_hora_salida")
    private LocalDateTime fechaHoraSalida;
    @Column(name = "fecha_hora_llegada")
    private LocalDateTime fechaHoraLlegada;
    private Float costoViaje;
    private Integer pasajeros;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conductor_id")
    private Conductor conductor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detalleviajeorigen_id")
    private ViajeOrigen viajeOrigen;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detalleviajedestino_id")
    private ViajeDestino viajeDestino;

}
