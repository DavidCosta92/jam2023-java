package com.example.JAM23.demo.auth.User;

import com.example.JAM23.demo.model.entities.InscriptionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name ="users")
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

    @Column(nullable = false , unique = true)
    String dni;

    @Column(nullable = false , unique = true)
    String email;

    String gender;

    @Enumerated(EnumType.STRING) // para obtener el nombre del rol y no el numero de rol
    Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<InscriptionEntity> inscription;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities(); // ESTE METODO TIENE LA LOGICA PARA OBTENER EL ROL Y LOS PERMISOS ASOCIADOS
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
    // TODOS ESTOS QUEDAN SETEADOS A TRUE, PORQUE SE VAN A VALIDAR EN EL JWT EN SI MISMO, pero este debemos agregar logica para bloquear users
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
