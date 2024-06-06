package org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.viajeDTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BuscarViajeOrigen {

    private String distritoorigen;
    private String distritodestino;
    private LocalDate fechaorigen;
}
