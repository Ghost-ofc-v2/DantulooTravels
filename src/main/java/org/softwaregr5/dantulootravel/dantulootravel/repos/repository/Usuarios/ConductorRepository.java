package org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Usuarios;

import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConductorRepository extends JpaRepository<Conductor, Long> {
}
