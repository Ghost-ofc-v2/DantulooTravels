package org.softwaregr5.dantulootravel.dantulootravel.repos.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios.Usuario;
import org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Usuarios.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import java.util.Optional;


@Component
public class JwtUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);
    private final UsuarioRepository usuarioRepository;

    public JwtUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("---loadUserByUsername called.---");
        Optional<Usuario> user = usuarioRepository.findByUsuario(username);

        if(user.isPresent()) {
            return user.get();
        } else {

            throw new UsernameNotFoundException("User "+username+" not found.");
        }
    }


}

