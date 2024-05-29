package org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@Table(name = "viajeorigen")
public class ViajeOrigen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idviajeorigen;

    private String departamentoorigen;
    private String ciudadorigen;
    private String provinciaorigen;
    private String direccionorigen;
    private String latitudorigen;
    private String longitudorigen;
}
