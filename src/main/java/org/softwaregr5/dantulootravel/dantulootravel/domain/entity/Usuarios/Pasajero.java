package org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Reservas.Reserva;

import java.util.List;

@Entity
@Table(name = "Pasajero")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pasajero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pasajero;

    @OneToMany(mappedBy = "pasajero")
    @JsonBackReference
    private List<Reserva> reservas;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    @JsonBackReference
    private Usuario usuario;
}
