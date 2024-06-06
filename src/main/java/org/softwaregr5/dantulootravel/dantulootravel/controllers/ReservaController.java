package org.softwaregr5.dantulootravel.dantulootravel.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.usuarioDTO.ReservarAsiento;
import org.softwaregr5.dantulootravel.dantulootravel.services.impl.ReservaService;
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

    @PostMapping("/reservar-viaje")
    public ResponseEntity<?> reserva(@RequestBody @Valid ReservarAsiento reservarAsiento) {
        try {
            String resp = reservaService.crearReserva(reservarAsiento);
            return new ResponseEntity<>(resp, HttpStatus.CREATED);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
