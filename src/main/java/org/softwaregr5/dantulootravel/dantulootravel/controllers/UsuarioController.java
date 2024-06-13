package org.softwaregr5.dantulootravel.dantulootravel.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.EmailDTO.EmailDTO;
import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.usuarioDTO.CambioContrasena;
import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.usuarioDTO.ConductorDTO;
import org.softwaregr5.dantulootravel.dantulootravel.domain.DTO.usuarioDTO.DatosRegistroUsuario;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios.Conductor;
import org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios.Usuario;
import org.softwaregr5.dantulootravel.dantulootravel.domain.mappers.LoginRequest;
import org.softwaregr5.dantulootravel.dantulootravel.domain.mappers.LoginResponse;
import org.softwaregr5.dantulootravel.dantulootravel.repos.repository.Usuarios.ConductorRepository;
import org.softwaregr5.dantulootravel.dantulootravel.repos.security.EncryptionUtil;
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

    @GetMapping("/email/{token}")
    public ResponseEntity<?> recuperaremail(@PathVariable("token") String token) {
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
            Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(email);
            if (usuarioOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }
            System.out.println(email);
            Usuario usuario = usuarioService.buscarusuario(usuarioOpt.get().getId_usuario());

            return ResponseEntity.ok(usuario);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/conductor/{idConductor}")
    public ResponseEntity<?> buscarConductor(@PathVariable Long idConductor) {
        try {
            System.out.println(idConductor);
            Conductor conductor = usuarioService.buscarconductor(idConductor);
            return ResponseEntity.ok(conductor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @GetMapping("/{usuarioId}/conductor")
    public ResponseEntity<?> buscarConductorPorUsuarioId(@PathVariable Long usuarioId) {
        try {
            Conductor conductor = usuarioService.buscarconductorPorUsuarioId(usuarioId);
            return ResponseEntity.ok(conductor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/conductor/guardar/{id}")
    public ResponseEntity<?> actualizarConductor(@PathVariable Long id, @RequestBody ConductorDTO conductor) {
        Optional<Conductor> conductorOpt = conductorRepository.findById(id);
        if (conductorOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conductor no encontrado");
        }

        Conductor conductorExistente = conductorOpt.get();
        conductorExistente.setMarcaAuto(conductor.getMarcaAuto());
        conductorExistente.setModeloAuto(conductor.getModeloAuto());
        conductorExistente.setPlacaAuto(conductor.getPlacaAuto());
        conductorExistente.setColorAuto(conductor.getColorAuto());

        conductorRepository.save(conductorExistente);
        return ResponseEntity.ok("Conductor actualizado exitosamente");
    }


    @GetMapping("/name")
    public ResponseEntity<?> buscarNombre(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String jwtToken = authorizationHeader.substring(7);
            String jwtToken2 = EncryptionUtil.decrypt(jwtToken);
            String email = jwtTokenUtil.getEmailFromToken(jwtToken2);

            if (!jwtTokenUtil.validateTokenEmail(jwtToken2, email)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }

            String nombre = usuarioService.recuperarNombre(email);
            return ResponseEntity.ok(nombre);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
