package com.esprit.usermicroservice.services;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;

public interface JwtService {

    String extractUsername(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String generateToken(UserDetails userDetails);

    String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    );

    String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    );

    boolean isTokenValid(String token, UserDetails userDetails);

    boolean isTokenExpired(String token);
}
