package org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes;

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
    private String latituddestino;
    private String longituddestino;

    @OneToOne(mappedBy = "viajeDestino", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Viajes viaje;
}
