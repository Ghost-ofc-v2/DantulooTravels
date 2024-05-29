package org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Viajes;

import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Viajes.Viajes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajesRepository extends JpaRepository<Viajes, Long> {
}
