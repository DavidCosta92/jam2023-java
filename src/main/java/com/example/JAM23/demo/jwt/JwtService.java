package com.example.JAM23.demo.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class JwtService {
    private static final String SECRET_KEY = "bWkga2V5LCBxdWUgZGViZXJpYSBlc3RhciBlbiBlbCBhcHAucHJvcGVydGllcyB5IGx1ZWdvIHNlZXIgaW1wb3J0YWRhIGFxdWk=";// "bWkga2V5LCBxdWUgZGViZXJpYSBlc3RhciBlbiBlbCBhcHAucHJvcGVydGllcyB5IGx1ZWdvIHNlZXIgaW1wb3J0YWRhIGFxdWk=";
    // "mi key, que deberia estar en el app.properties y luego seer importada aqui";

    public String getToken(UserDetails user) {

        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24)) // este tiempo de exp, deberia importarlo desde aplication properties
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
