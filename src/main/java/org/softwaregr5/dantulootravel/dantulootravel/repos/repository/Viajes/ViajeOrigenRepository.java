package org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Viajes;

import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes.ViajeOrigen;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes.Viajes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ViajeOrigenRepository extends JpaRepository<ViajeOrigen, Long> {
    @Query("SELECT v, vo, vd FROM Viajes v " +
            "JOIN FETCH v.viajeOrigen vo " +
            "JOIN FETCH v.viajeDestino vd " +
            "WHERE vo.distritoorigen = :distritoOrigen " +
            "AND vd.distritodestino = :distritoDestino " +
            "AND FUNCTION('DATE', v.fechaHoraSalida) = :fecha")
    List<Viajes> findViajesWithOrigenAndDestinoByCriteria(String distritoOrigen, String distritoDestino, LocalDate fecha);


}
