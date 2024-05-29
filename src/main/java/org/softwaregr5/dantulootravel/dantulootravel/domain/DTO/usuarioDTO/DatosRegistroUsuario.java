package org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.usuarioDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.validator.constraints.Length;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios.Rol;

import java.sql.Date;

public record DatosRegistroUsuario(
        @NotBlank @Length(max = 100) String nombre,
        @NotNull  Date fecha_nacimiento,
        @NotNull @Size(min = 0, max = 9) String telefono,
        @NotBlank @Email @Length(max = 100) String correo,
        @NotBlank @Length(max = 100) String usuario,
        @NotBlank @Length(max = 100) String contrasena,
        @NotNull Rol role
){
}
