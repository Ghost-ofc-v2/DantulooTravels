package org.softwaregr5.dantulootravel.dantulootravel.services.impl;


import lombok.RequiredArgsConstructor;
import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.viajeDTO.BuscarViajeOrigen;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes.ViajeDestino;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes.ViajeOrigen;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes.Viajes;
import org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Viajes.ViajeOrigenRepository;
import org.softwaregr5.dantulootravel.dantulootravel.repos.security.JwtTokenUtil;
import org.softwaregr5.dantulootravel.dantulootravel.services.ViajeOrigenService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ViajeOrigenImpl implements ViajeOrigenService {

    private ViajeOrigenRepository viajeOrigenRepository;
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    public ViajeOrigenImpl(ViajeOrigenRepository viajeOrigenRepository) {
        this.viajeOrigenRepository = viajeOrigenRepository;
    }

    @Override
    public List<Viajes> buscarViajeOrigen(BuscarViajeOrigen buscarViajeOrigen) {
        return viajeOrigenRepository.findViajesWithOrigenAndDestinoByCriteria(buscarViajeOrigen.getCiudadorigen(), buscarViajeOrigen.getCiudaddestino(), buscarViajeOrigen.getFechaorigen());
    }
}
