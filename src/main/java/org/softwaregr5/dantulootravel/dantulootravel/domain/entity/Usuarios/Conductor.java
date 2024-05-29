package org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes.Viajes;

@Getter
@Setter
@Entity
@Table(name = "conductor", uniqueConstraints = {@UniqueConstraint(columnNames = {"dni"})})
public class Conductor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idConductor;
    Long dni;
    String marcaAuto;
    String modeloAuto;
    String placaAuto;
    String color;
    Double calificacionPromedio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "viaje_id")
    private Viajes viaje;


}
