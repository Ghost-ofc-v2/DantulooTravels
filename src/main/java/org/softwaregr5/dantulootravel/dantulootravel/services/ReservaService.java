package org.softwaregr5.dantulootravel.dantulootravel.services;

import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.usuarioDTO.ReservarAsiento;

public interface ReservaService {
    String crearReserva(ReservarAsiento reservarAsiento);
    String actualizarEstadoReserva(Long reservaId, String estado);
}
