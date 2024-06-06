package org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(name = "fecha_hora_salida")
    private LocalDateTime fechaHoraSalida;
    @Column(name = "fecha_hora_llegada")
    private LocalDateTime fechaHoraLlegada;
    private Float costoViaje;
    private Integer pasajeros;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conductor_id")
    @JsonBackReference
    private Conductor conductor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detalleviajeorigen_id")

    private ViajeOrigen viajeOrigen;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detalleviajedestino_id")

    private ViajeDestino viajeDestino;

    @Override
    public String toString() {
        return "Viajes{" +
                "id_viajes=" + id_viajes +
                ", fechaHoraSalida=" + fechaHoraSalida +
                ", fechaHoraLlegada=" + fechaHoraLlegada +
                ", costoViaje=" + costoViaje +
                ", pasajeros=" + pasajeros +
                ", viajeOrigen=" + viajeOrigen +
                ", viajeDestino=" + viajeDestino +
                '}';
    }

}
