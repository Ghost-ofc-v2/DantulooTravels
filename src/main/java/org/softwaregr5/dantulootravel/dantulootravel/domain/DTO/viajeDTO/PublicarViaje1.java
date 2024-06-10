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
    private String direcciondestino;
    private String paisdestino;


    private String departamentoorigen;
    private String ciudadorigen;
    private String direccionorigen;
    private String paisorigen;



}
