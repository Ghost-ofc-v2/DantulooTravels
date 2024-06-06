package org.softwaregr5.dantulootravel.dantulootravel.services.impl;

import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.usuarioDTO.ReservarAsiento;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Reservas.Reserva;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios.Pasajero;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes.Viajes;
import org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Reservas.ReservasRepository;
import org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Usuarios.PasajeroRepository;
import org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Viajes.ViajesRepository;
import org.softwaregr5.dantulootravel.dantulootravel.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ViajesRepository viajesRepository;

    @Autowired
    private ReservasRepository reservaRepository;

    @Autowired
    private PasajeroRepository pasajeroRepository;


    @Override
    public String crearReserva(ReservarAsiento reservarAsiento) {


        Viajes viaje = viajesRepository.findById(reservarAsiento.getIdviaje()).orElseThrow(() -> new RuntimeException("Viaje no encontrado"));
        if (!viaje.reservarAsientos(reservarAsiento.getAsientosReservados())) {
            return "No hay suficientes asientos disponibles.";
        }
        Pasajero pasajero = pasajeroRepository.findById(reservarAsiento.getIdpasajero()).orElseThrow(() -> new RuntimeException("Pasajero no encontrado"));

        Reserva reserva = new Reserva();
        reserva.setPasajero(pasajero);
        reserva.setViajes(viaje);
        reserva.setCantidadAsientos(reservarAsiento.getAsientosReservados());
        reserva.setEstado("PENDIENTE");
        reservaRepository.save(reserva);
        return "Reserva creada con éxito.";
    }

    @Override
    public String actualizarEstadoReserva(Long reservaId, String estado) {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reserva.setEstado(estado);
        reservaRepository.save(reserva);
        return "Estado de la reserva actualizado a " + estado;
    }
}
