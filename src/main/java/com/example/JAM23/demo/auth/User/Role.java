package com.example.JAM23.demo.auth.User;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {
    ADMIN(
            Set.of(
                    Permission.READ_COURSES,
                    Permission.EDIT_COURSES,
                    Permission.DELETE_COURSES
            )
    ),
    USER (Collections.emptySet()),
    TEACHER(
            Set.of(
                    Permission.READ_COURSES,
                    Permission.EDIT_COURSES
            )
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities(){
        List<SimpleGrantedAuthority> auths = getPermissions()
                .stream()
                .map(permision -> new SimpleGrantedAuthority(permision.name()))
                .collect(Collectors.toList());
        auths.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return  auths;
    }
}

