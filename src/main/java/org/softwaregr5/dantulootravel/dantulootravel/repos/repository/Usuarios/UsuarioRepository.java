package org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Usuarios;

import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByCorreo(String correo);
    Optional<Usuario> findByTelefono(String telefono);
    Optional<Usuario> findByUsuario(String usuario);
}
