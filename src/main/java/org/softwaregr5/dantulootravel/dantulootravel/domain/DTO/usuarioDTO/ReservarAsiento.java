package org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.usuarioDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservarAsiento {

    @NotNull(message = "El ID del viaje no puede ser nulo")
    private Long idviaje;

    @NotNull(message = "El ID del pasajero no puede ser nulo")
    private Long idpasajero;

    @NotNull(message = "La cantidad de asientos reservados no puede ser nula")
    @Min(value = 1, message = "Debe reservar al menos un asiento")
    private Integer asientosReservados;
}
