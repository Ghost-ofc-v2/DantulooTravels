package org.softwaregr5.dantulootravel.dantulootravel.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.usuarioDTO.ReservarAsiento;
import org.softwaregr5.dantulootravel.dantulootravel.services.ReservaService;
import org.softwaregr5.dantulootravel.dantulootravel.services.impl.ReservaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reserva")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ReservaController {

    @Autowired
    private final ReservaService reservaService;



    @PutMapping("/{reservaId}/aceptar")
    public ResponseEntity<String> aceptarReserva(@PathVariable Long reservaId) {
        return ResponseEntity.ok(reservaService.actualizarEstadoReserva(reservaId, "ACEPTADA"));
    }

    @PutMapping("/{reservaId}/rechazar")
    public ResponseEntity<String> rechazarReserva(@PathVariable Long reservaId) {
        return ResponseEntity.ok(reservaService.actualizarEstadoReserva(reservaId, "RECHAZADA"));
    }
}
