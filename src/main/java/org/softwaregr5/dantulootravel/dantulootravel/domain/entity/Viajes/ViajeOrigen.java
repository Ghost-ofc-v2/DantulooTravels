package org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private String distritoorigen;
    private String latitudorigen;
    private String longitudorigen;


    @OneToOne(mappedBy = "viajeOrigen", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Viajes viaje;


    @Override
    public String toString() {
        return "ViajeOrigen{" +
                "idviajeorigen=" + idviajeorigen +
                ", distritoorigen='" + distritoorigen + '\'' +
                ", latitudorigen=" + latitudorigen +
                ", longitudorigen=" + longitudorigen +
                // No incluir 'viaje' para evitar recursión
                '}';
    }
}
