package org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Mensajeria;

import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Mensajeria.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByChatId(Long chatId);
}
