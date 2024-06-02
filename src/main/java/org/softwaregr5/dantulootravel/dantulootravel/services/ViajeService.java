package org.softwaregr5.dantulootravel.dantulootravel.services;

import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.viajeDTO.PublicarViaje1;
import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.viajeDTO.PublicarViaje2;

public interface ViajeService {

    Long publicarViajev1(PublicarViaje1 publicarViaje1);
    String publicarViaje2(Long viajeId, PublicarViaje2 publicarViaje2);
}
