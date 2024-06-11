package org.softwaregr5.dantulootravel.dantulootravel.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.EmailDTO.EmailDTO;
import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.usuarioDTO.CambioContrasena;
import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.usuarioDTO.DatosRegistroUsuario;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios.Conductor;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios.Usuario;
import org.softwaregr5.dantulootravel.dantulootravel.domain.mappers.LoginRequest;
import org.softwaregr5.dantulootravel.dantulootravel.domain.mappers.LoginResponse;
import org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Usuarios.ConductorRepository;
import org.softwaregr5.dantulootravel.dantulootravel.repos.security.JwtTokenUtil;
import org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Usuarios.UsuarioRepository;
import org.softwaregr5.dantulootravel.dantulootravel.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private ConductorRepository conductorRepository;


    @Autowired
    public UsuarioController(final UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> guardarUsuario(@RequestBody @Valid DatosRegistroUsuario datos) {
        try {
            return ResponseEntity.ok(usuarioService.guardarUsuario(datos));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<?> loginusuario(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            LoginResponse tokenres = usuarioService.loginusuario(loginRequest);
            return  ResponseEntity.ok(tokenres);
        }catch (UsernameNotFoundException e) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado.");
        }
    }



    @PostMapping("/recuperar-contrasena")
    public ResponseEntity<?> recuperarContrasena2(@RequestBody @Valid EmailDTO email) throws Exception {
        try {
            usuarioService.olvideContrasena(email);
            return new ResponseEntity<>("Enviado Correctamente", HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ver-cambio-contrasena")
    public ResponseEntity<?> vercambioContrasena(@RequestParam("token") String token){
        try{
            if (!jwtTokenUtil.validateTokenEmail(token, jwtTokenUtil.getEmailFromToken(token))) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }
            return ResponseEntity.ok("Show reset password form/page");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/cambiar-contrasena")
    public ResponseEntity<?> cambiarContrasena1(@RequestParam("token") String token, @RequestBody CambioContrasena contrasena) {
        try{
            String email = jwtTokenUtil.getEmailFromToken(token);
            if(!jwtTokenUtil.validateTokenEmail(token, email)){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }
            usuarioService.cambiarContrasena(email, contrasena.getPassword());

            return ResponseEntity.ok("Contraseña Actualizada correctamente");
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/email")
    public ResponseEntity<?> recuperaremail(@RequestParam("token") String token){
        try{
            if (!jwtTokenUtil.validateTokenEmail(token, jwtTokenUtil.getEmailFromToken(token))) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }
            String email = jwtTokenUtil.getEmailFromToken(token);
            return ResponseEntity.ok(email);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> recuperarUsuario(@PathVariable String email){
        try {
            Optional<Usuario> iduser = usuarioRepository.findByCorreo(email);

            Usuario usuario = usuarioService.buscarusuario(iduser.get().getId_usuario());
            return ResponseEntity.ok(usuario);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/conductor/{idConductor}")
    public ResponseEntity<?> buscarConductor(@PathVariable Long idConductor) {
        try {
            Conductor conductor = usuarioService.buscarconductor(idConductor);
            return ResponseEntity.ok(conductor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @GetMapping("/usuario/{usuarioId}/conductor")
    public ResponseEntity<?> buscarConductorPorUsuarioId(@PathVariable Long usuarioId) {
        try {
            Conductor conductor = usuarioService.buscarconductorPorUsuarioId(usuarioId);
            return ResponseEntity.ok(conductor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
