package com.example.JAM23.demo.auth.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="user" , uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails {
    @Id
    @GeneratedValue
    Integer id;

    @Column(nullable = false)
    String username;

    String lastName;
    String firstName;
    String country;
    String password;

    @Enumerated(EnumType.STRING) // para obtener el nombre del rol y no el numero de rol
    Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // En este ejemplo, solo retorna el rol de usuario como si fuera un permiso,
        // en otros ejemplos, en vez de roles se pasan permisos especificos, ej: puede_guardar_productos
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    // TODOS ESTOS QUEDAN SETEADOS A TRUE, PORQUE SE VAN A VALIDAR EN EL JWT EN SI MISMO
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    // TODOS ESTOS QUEDAN SETEADOS A TRUE, PORQUE SE VAN A VALIDAR EN EL JWT EN SI MISMO
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    // TODOS ESTOS QUEDAN SETEADOS A TRUE, PORQUE SE VAN A VALIDAR EN EL JWT EN SI MISMO
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    // TODOS ESTOS QUEDAN SETEADOS A TRUE, PORQUE SE VAN A VALIDAR EN EL JWT EN SI MISMO
    @Override
    public boolean isEnabled() {
        return true;
    }
}
