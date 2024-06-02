package org.softwaregr5.dantulootravel.dantulootravel.services;

import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.viajeDTO.PublicarViaje1;
import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.viajeDTO.PublicarViaje2;

public interface ViajeService {

    Long publicarViajev1(String jwtToken, PublicarViaje1 publicarViaje1);
    String publicarViaje2(String jwtToken, Long viajeId, PublicarViaje2 publicarViaje2);
}
