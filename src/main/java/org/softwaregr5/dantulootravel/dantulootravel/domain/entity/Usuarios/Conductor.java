package org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes.Viajes;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "conductor")
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    @JsonBackReference
    private Usuario usuario;


    @OneToMany(mappedBy = "conductor")
    @JsonBackReference
    private List<Viajes> viaje;


}
