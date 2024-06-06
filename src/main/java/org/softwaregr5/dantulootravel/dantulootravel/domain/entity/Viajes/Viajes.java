package org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Reservas.Reserva;
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
    private Integer totalAsientos;
    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer asientosReservados;
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean disponible;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conductor_id", referencedColumnName = "idConductor")
    @JsonBackReference
    private Conductor conductor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detalleviajeorigen_id")
    private ViajeOrigen viajeOrigen;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detalleviajedestino_id")
    private ViajeDestino viajeDestino;

    @OneToMany(mappedBy = "viajes")
    @JsonBackReference
    private List<Reserva> reservas;


    public boolean reservarAsientos(int numeroAsientos) {
        if (this.disponible && (this.asientosReservados + numeroAsientos <= this.totalAsientos)) {
            this.asientosReservados += numeroAsientos;
            if (this.asientosReservados == this.totalAsientos) {
                this.disponible = false;
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Viajes{" +
                "id_viajes=" + id_viajes +
                ", fechaHoraSalida=" + fechaHoraSalida +
                ", fechaHoraLlegada=" + fechaHoraLlegada +
                ", costoViaje=" + costoViaje +
                ", totalAsientos=" + totalAsientos +
                ", asientosReservados=" + asientosReservados +
                ", disponible=" + disponible +
                ", viajeOrigen=" + viajeOrigen +
                ", viajeDestino=" + viajeDestino +
                '}';
    }

}
