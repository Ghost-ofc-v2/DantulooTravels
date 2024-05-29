package org.softwaregr5.dantulootravel.dantulootravel.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.viajeDTO.PublicarViaje1;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes.ViajeDestino;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes.ViajeOrigen;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes.Viajes;
import org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Viajes.ViajesRepository;
import org.softwaregr5.dantulootravel.dantulootravel.services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViajeServiceImpl implements ViajeService {

    private final ViajesRepository viajesRepository;




    @Override
    public String publicarViajev1(PublicarViaje1 publicarViaje1){
        try{
            Viajes viajes = new Viajes();

            ViajeOrigen viajeOrigen = new ViajeOrigen();
            viajeOrigen.setDireccionorigen(publicarViaje1.getDireccionorigen());

            ViajeDestino viajeDestino = new ViajeDestino();
            viajeDestino.setDirecciondestino(publicarViaje1.getDirecciondestino());

            viajes.setViajeOrigen(viajeOrigen);
            viajes.setViajeDestino(viajeDestino);

            viajes.setFechaHoraSalida(publicarViaje1.getFechaHoraSalida());
            viajes.setCostoViaje(publicarViaje1.getPrecio());
            viajes.setPasajeros(publicarViaje1.getPasajeros());

            viajesRepository.save(viajes);

            return "Primera parte del viaje realizado";
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Error al publicar el viaje: "+ e.getMessage());
        }catch (Exception e){
            throw new RuntimeException("Error al publicar el viaje: "+ e.getMessage());
        }

    }

    public String publicarViaje2(){
        return "Segunda parte del viaje realizado";
    }

}
