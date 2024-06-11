package org.softwaregr5.dantulootravel.dantulootravel.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.usuarioDTO.ReservarAsiento;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Reservas.Reserva;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios.Pasajero;

import org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Reservas.ReservasRepository;
import org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Usuarios.PasajeroRepository;

import org.softwaregr5.dantulootravel.dantulootravel.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {


    @Autowired
    private ReservasRepository reservaRepository;

    @Autowired
    private PasajeroRepository pasajeroRepository;



    @Override
    public String actualizarEstadoReserva(Long reservaId, String estado) {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reserva.setEstado(estado);
        reservaRepository.save(reserva);
        return "Estado de la reserva actualizado a " + estado;
    }
}
