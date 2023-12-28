package com.example.JAM23.demo.config;

import com.example.JAM23.demo.auth.User.Permission;
import com.example.JAM23.demo.auth.User.Role;
import com.example.JAM23.demo.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity (prePostEnabled = true, securedEnabled = true) // => PERMITITE =>  @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')") //@Secured({ "ADMIN", "TEACHER" })
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf-> csrf.disable()).headers().disable()
                //.headers().httpStrictTransportSecurity().maxAgeInSeconds(0).includeSubDomains(true)
                //.headers().httpStrictTransportSecurity().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/docs/swagger-ui/**").permitAll()
                        //.requestMatchers("/course/**").hasAnyRole(Role.ADMIN.name() , Role.TEACHER.name())
                        //.requestMatchers(HttpMethod.GET,"/course/**").hasAnyAuthority(Permission.READ_COURSES.name())
                        //.requestMatchers("/course/**").permitAll()
                        //.requestMatchers("/inscription/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sesionMan->
                        sesionMan.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter , UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
