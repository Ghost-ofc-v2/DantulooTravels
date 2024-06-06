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
@Table(name = "viajedestino")
public class ViajeDestino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idviajedestino;

    private String departamentodestino;
    private String ciudaddestino;
    private String provinciadestino;
    private String direcciondestino;
    private String distritodestino;
    private String latituddestino;
    private String longituddestino;


    @OneToOne(mappedBy = "viajeDestino", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Viajes viaje;

    @Override
    public String toString() {
        return "ViajeDestino{" +
                "idviajedestino=" + idviajedestino +
                ", distritodestino='" + distritodestino + '\'' +
                ", latituddestino=" + latituddestino +
                ", longituddestino=" + longituddestino +
                // No incluir 'viaje' para evitar recursión
                '}';
    }
}
