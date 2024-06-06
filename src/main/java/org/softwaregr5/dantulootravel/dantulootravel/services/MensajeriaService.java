package org.softwaregr5.dantulootravel.dantulootravel.services;

import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Mensajeria.Mensaje;

import java.util.List;

public interface MensajeriaService {

    String enviarMensaje(Mensaje mensaje);
    List<Mensaje> obtenerMensajesPorChat(Long chatId);
}
