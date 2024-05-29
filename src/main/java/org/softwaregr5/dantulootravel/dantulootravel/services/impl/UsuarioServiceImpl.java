package org.softwaregr5.dantulootravel.dantulootravel.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.EmailDTO.EmailDTO;
import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.usuarioDTO.DatosRegistroUsuario;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios.Rol;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios.Usuario;
import org.softwaregr5.dantulootravel.dantulootravel.domain.mappers.LoginRequest;
import org.softwaregr5.dantulootravel.dantulootravel.domain.mappers.LoginResponse;
import org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Usuarios.UsuarioRepository;
import org.softwaregr5.dantulootravel.dantulootravel.repos.security.EncryptionUtil;
import org.softwaregr5.dantulootravel.dantulootravel.repos.security.JwtTokenUtil;
import org.softwaregr5.dantulootravel.dantulootravel.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TemplateEngine templateEngine;
    private final JavaMailSender mailSender;


    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public LoginResponse loginusuario(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Usuario user = usuarioRepository.findByUsuario(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: "+ loginRequest.getUsername())) ;

        String token = jwtTokenUtil.generateToken(user);
        String encryptedToken = EncryptionUtil.encrypt(token);
        return new LoginResponse(encryptedToken);
    }


    @Override
    public String guardarUsuario(DatosRegistroUsuario datos){
        Usuario usuario = new Usuario();
        usuario.setUsuario(datos.usuario());
        usuario.setNombre(datos.nombre());
        usuario.setCorreo(datos.correo());
        usuario.setContrasena(passwordEncoder.encode(datos.contrasena()));
        usuario.setTelefono(datos.telefono());
        usuario.setFecha_nacimiento(datos.fecha_nacimiento());
        Rol role = datos.role();

        if(role == Rol.CLIENTE || role == Rol.CONDUCTOR){
            usuario.setRole(role);
        }else{
            throw new IllegalArgumentException("Rol no valido");
        }

        usuarioRepository.save(usuario);

        return  "Usuario Guardado con exito: " + usuario.getNombre();

    }


    @Override
    public void olvideContrasena(EmailDTO email) {

        String token = jwtTokenUtil.generateToken(email.getDestinatario());
        String resetUrl = "http://localhost:8080/reset-password?token=" + token;

        Context context = new Context();
        context.setVariable("mensaje", resetUrl);

        String html = templateEngine.process("email", context);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(email.getDestinatario());
            helper.setSubject("Password Reset Request");
            helper.setText(html, true);
            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Error sending email: " + e.getMessage(), e);
        }
    }

    @Override
    public void cambiarContrasena(String email, String newPassword){
        List<Usuario> usuarios = usuarioRepository.findByCorreo(email);

        if(usuarios.isEmpty()){
            throw new IllegalArgumentException("Usuario no encontrado: " + email);
        }
        Usuario usuario = usuarios.get(0);
        usuario.setContrasena(passwordEncoder.encode(newPassword));
        usuarioRepository.save(usuario);

    }

    public boolean esImagenBase64Valida(String base64Str) {
        if (base64Str.startsWith("data:image/")) {
            String[] parts = base64Str.split(",");
            if (parts.length == 2 && Base64.isBase64(parts[1].getBytes(StandardCharsets.UTF_8))) {
                return true;
            }
        }
        return false;
    }



}
