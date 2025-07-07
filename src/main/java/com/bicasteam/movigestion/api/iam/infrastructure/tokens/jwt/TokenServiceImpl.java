package com.bicasteam.movigestion.api.iam.infrastructure.tokens.jwt;

import com.bicasteam.movigestion.api.iam.domain.model.aggregates.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements BearerTokenService {

    @Value("${authorization.jwt.secret}")
    private String secret;

    @Value("${authorization.jwt.expiration.ms}")
    private long expirationMs;

    @Override
    public String generateToken(User user) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expirationMs))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }
}