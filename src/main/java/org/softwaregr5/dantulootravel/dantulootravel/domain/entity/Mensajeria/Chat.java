package org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Mensajeria;

import jakarta.persistence.*;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Reservas.Reserva;

import java.util.List;

@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_chat;

    @OneToMany(mappedBy = "chat")
    private List<Mensaje> mensajes;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;
}
