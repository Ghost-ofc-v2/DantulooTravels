package org.softwaregr5.dantulootravel.dantulootravel.domain.mappers;

import lombok.*;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios.Rol;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    String token;
    Rol role;
}