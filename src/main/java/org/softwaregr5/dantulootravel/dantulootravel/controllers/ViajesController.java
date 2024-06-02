package org.softwaregr5.dantulootravel.dantulootravel.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.viajeDTO.PublicarViaje1;
import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.viajeDTO.PublicarViaje2;
import org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Viajes.ViajesRepository;
import org.softwaregr5.dantulootravel.dantulootravel.services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/viajes")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ViajesController {

    @Autowired
    private ViajesRepository viajesRepository;

    private ViajeService viajeService;

    @Autowired
    public ViajesController(ViajesRepository viajesRepository, ViajeService viajeService) {
        this.viajesRepository = viajesRepository;
        this.viajeService = viajeService;
    }

    @PostMapping("/publicar-viaje")
    @Transactional
    public ResponseEntity<?> publicarViaje(@RequestBody @Valid PublicarViaje1 publicarViaje1) {
        try{
            Long idviaje = viajeService.publicarViajev1(publicarViaje1);
            String mensajeres = "ID del viaje: " + idviaje + "\n";
            return ResponseEntity.ok(mensajeres);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (NullPointerException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/publicar-viaje2/{viajeId}")
    @Transactional
    public ResponseEntity<?> publicarViaje2(@PathVariable Long viajeId,@RequestBody @Valid  PublicarViaje2 publicarViaje2){
        try {
            String message = viajeService.publicarViaje2(viajeId, publicarViaje2);
            return ResponseEntity.ok(message);
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
