package org.softwaregr5.dantulootravel.dantulootravel.services.impl;

import lombok.Getter;
import lombok.Setter;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Mensajeria.Mensaje;
import org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Mensajeria.MensajeRepository;
import org.softwaregr5.dantulootravel.dantulootravel.services.MensajeriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MensajeriaServiceImpl implements MensajeriaService {

    @Autowired
    private final MensajeRepository mensajeRepository;

    public MensajeriaServiceImpl(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
    }

    @Override
    public String enviarMensaje(Mensaje mensaje) {
        mensaje.setFechaEnvio(new Date());
        mensajeRepository.save(mensaje);
        return "Mensaje enviado com sucesso!";
    }

    @Override
    public List<Mensaje> obtenerMensajesPorChat(Long chatId) {
        return mensajeRepository.findByChatId(chatId);
    }
}
