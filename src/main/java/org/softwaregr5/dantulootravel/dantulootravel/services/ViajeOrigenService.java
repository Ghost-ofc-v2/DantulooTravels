package org.softwaregr5.dantulootravel.dantulootravel.services;

import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.viajeDTO.BuscarViajeOrigen;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes.ViajeOrigen;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes.Viajes;

import java.util.List;

public interface ViajeOrigenService {

    List<Viajes> buscarViajeOrigen(BuscarViajeOrigen buscarViajeOrigen);
}
