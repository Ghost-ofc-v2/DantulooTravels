package org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Usuarios;

import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasajeroRepository extends JpaRepository<Pasajero, Long> {
}
