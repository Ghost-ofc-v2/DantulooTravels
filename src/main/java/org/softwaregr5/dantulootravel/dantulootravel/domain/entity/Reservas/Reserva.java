package org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Reservas;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios.Pasajero;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes.Viajes;

@Entity
@Table(name = "reserva")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pasajero_id", referencedColumnName = "id_pasajero")
    @JsonManagedReference
    private Pasajero pasajero;

    @ManyToOne
    private Viajes viajes;

    private Integer cantidadAsientos;


}
