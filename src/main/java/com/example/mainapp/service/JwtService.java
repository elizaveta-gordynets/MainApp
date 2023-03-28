package com.example.mainapp.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.mainapp.dto.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Calendar;

@Slf4j
@Service
public class JwtService {

    private static final String SECRET_KEY = "S3cret";
    private final JWTVerifier verifier;
    private final Algorithm HS512;

    public JwtService() {
        this.HS512 = Algorithm.HMAC512(SECRET_KEY);
        this.verifier = JWT.require(HS512).build();
    }

    public String generateAccessToken(CustomUserDetails user) {
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.YEAR, 1);
        return JWT.create()
                .withSubject(String.format("%s", user.getUsername()))
                .withIssuedAt(Instant.now())
                .withExpiresAt(expiration.toInstant())
                .sign(this.HS512);
    }

    public String validateToken(final String token) {
        try{
           String tok = verifier.verify(token).getSubject();
            return tok;
        } catch (JWTVerificationException e) {
            log.error("Authentication token is invalid");
            return null;
        }
    }
}
