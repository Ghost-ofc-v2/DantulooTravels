package org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Mensajeria;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios.Usuario;

import java.util.Date;

@Entity
@Table(name = "mensaje")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mensaje;

    @ManyToOne
    @JoinColumn(name = "emisor_id")
    private Usuario emisor;

    @ManyToOne
    @JoinColumn(name = "receptor_id")
    private Usuario receptor;

    private String contenido;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio = new Date();
}
