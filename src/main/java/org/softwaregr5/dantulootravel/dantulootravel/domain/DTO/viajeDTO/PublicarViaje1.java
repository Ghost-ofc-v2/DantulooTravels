package org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.viajeDTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PublicarViaje1 {
    private String direccionorigen;
    private String direcciondestino;

    private Integer pasajeros;
    private Float precio;
    private LocalDateTime fechaHoraSalida;



}
