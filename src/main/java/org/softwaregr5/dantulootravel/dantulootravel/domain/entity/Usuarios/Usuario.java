package org.softwaregr5.dantulootravel.dantulootravel.domain.entity.Usuarios;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import jakarta.persistence.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.sql.Date;
import java.util.List;


@Entity(name = "Usuario")
@Table(name = "usuario", uniqueConstraints = {@UniqueConstraint(columnNames = {"correo", "dni"})})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;
    private String contrasena;
    @Column(unique = true)
    private String correo;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Rol role;
    @Column(unique = true)
    private String telefono;
    private Date fecha_nacimiento;
    private Long dni;
    private String sexo;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Conductor conductor;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return correo;
    }
    @Override
    public String getPassword() {
        return contrasena;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}

