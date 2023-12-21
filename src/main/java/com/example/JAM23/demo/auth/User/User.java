package com.example.JAM23.demo.auth.User;

import com.example.JAM23.demo.model.entities.CourseEntity;
import com.example.JAM23.demo.model.entities.InscriptionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name ="user")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"username" , "dni" , "email"})})
public class User implements UserDetails {
    @Id
    @GeneratedValue
    Integer id;

    @Column(nullable = false)
    String username;

    String lastName;
    String firstName;
    String password;
    String phone;

    @Column(nullable = false)
    String dni;

    @Column(nullable = false)
    String email;

    String gender;

    @Enumerated(EnumType.STRING) // para obtener el nombre del rol y no el numero de rol
    Role role;

    /*
    @ManyToMany(cascade = CascadeType.ALL, targetEntity = CourseEntity.class)
    @JoinTable(name = "inscripciones",
            joinColumns = @JoinColumn(name = "id_user_fk", nullable = true), // jpa crea estos atributos automaticamente en las entidades
            inverseJoinColumns = @JoinColumn(name = "id_course_fk", nullable = true)) // jpa crea estos atributos automaticamente en las entidades
    private Set<CourseEntity> courses = new HashSet<>();
    */
    @OneToMany(mappedBy = "user")
    private Set<InscriptionEntity> inscription;

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
