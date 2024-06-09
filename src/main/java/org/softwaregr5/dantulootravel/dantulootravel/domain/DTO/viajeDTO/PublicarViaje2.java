package org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.viajeDTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PublicarViaje2 {

    private String marcaAuto;
    private String modeloAuto;
    private String placaAuto;
    private String colorAuto;

    private Integer pasajeros;
    private Float precio;
    private LocalDateTime fechaHoraSalida;

}
