package org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Reservas;

import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Reservas.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservasRepository extends JpaRepository<Reserva, Long> {
}
