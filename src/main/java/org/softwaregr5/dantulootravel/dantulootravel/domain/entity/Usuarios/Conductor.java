package org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios;

import jakarta.persistence.*;
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
    @Column(name = "marca_auto")
    String marcaAuto;
    @Column(name = "modelo_auto")
    String modeloAuto;
    @Column(name = "placa_auto")
    String placaAuto;
    @Column(name = "color_auto")
    String colorAuto;
    Double calificacionPromedio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "viaje_id")
    private Viajes viaje;


}
