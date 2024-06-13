package org.softwaregr5.dantulootravel.dantulootravel.services;

import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.EmailDTO.EmailDTO;
import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.usuarioDTO.DatosRegistroUsuario;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios.Conductor;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios.Usuario;
import org.softwaregr5.dantulootravel.dantulootravel.domain.mappers.LoginRequest;
import org.softwaregr5.dantulootravel.dantulootravel.domain.mappers.LoginResponse;

public interface UsuarioService {
    LoginResponse loginusuario(LoginRequest loginRequest);

    String guardarUsuario(DatosRegistroUsuario datos);
    void olvideContrasena(EmailDTO email);
    void cambiarContrasena(String email, String newPassword);
    Usuario buscarusuario(Long idusuario);
    Conductor buscarconductor(Long idconductor);
    Conductor buscarconductorPorUsuarioId(Long usuarioId);
    String recuperarNombre(String email);
}
