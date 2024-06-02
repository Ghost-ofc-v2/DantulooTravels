package org.softwaregr5.dantulootravel.dantulootravel.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.viajeDTO.PublicarViaje1;
import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.viajeDTO.PublicarViaje2;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios.Conductor;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes.ViajeDestino;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes.ViajeOrigen;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes.Viajes;
import org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Usuarios.ConductorRepository;
import org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Viajes.ViajesRepository;
import org.softwaregr5.dantulootravel.dantulootravel.services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViajeServiceImpl implements ViajeService {

    private final ViajesRepository viajesRepository;

    private final ConductorRepository conductorRepository;


    @Override
    public Long publicarViajev1(PublicarViaje1 publicarViaje1){
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


            return viajes.getId_viajes();
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Error al publicar el viaje: "+ e.getMessage());
        }catch (Exception e){
            throw new RuntimeException("Error al publicar el viaje: "+ e.getMessage());
        }

    }

    @Override
    public String publicarViaje2(Long viajeId, PublicarViaje2 publicarViaje2){

        try{
            Viajes viajes = viajesRepository.findById(viajeId)
                    .orElseThrow(() -> new IllegalArgumentException("El viaje no existe"));

            Conductor conductor = viajes.getConductor();
            if (conductor == null) {
                return "El conductor no existe";
            }

            conductor.setMarcaAuto(publicarViaje2.getMarcaAuto());
            conductor.setModeloAuto(publicarViaje2.getModeloAuto());
            conductor.setPlacaAuto(publicarViaje2.getPlacaAuto());
            conductor.setColorAuto(publicarViaje2.getColorAuto());
            conductorRepository.save(conductor);
            if (viajes.getConductor() == null) {
                viajes.setConductor(conductor);
                viajesRepository.save(viajes);
            }

            return "Detalles del conductor actualizados y viaje actualizado exitosamente para el viaje ID: " + viajeId;
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar el viaje: " + e.getMessage());
        }

    }

}
