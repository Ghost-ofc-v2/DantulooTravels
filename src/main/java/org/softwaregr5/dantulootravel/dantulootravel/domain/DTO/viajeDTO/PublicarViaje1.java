package org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.viajeDTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PublicarViaje1 {
    private String departamentodestino;
    private String ciudaddestino;
    private String provinciadestino;
    private String direcciondestino;
    private String distritodestino;

    private String departamentoorigen;
    private String ciudadorigen;
    private String provinciaorigen;
    private String direccionorigen;
    private String distritoorigen;

    private Integer pasajeros;
    private Float precio;
    private LocalDateTime fechaHoraSalida;



}
