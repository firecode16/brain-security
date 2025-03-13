package com.brain.security.config;

import com.brain.security.model.User;
import com.brain.security.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author firecode16
 *
 */
@Component
public class JwtConfig {
    @Autowired
    private UserRepository usuarioRepository;

    @Value("${security.jwt.expiration-time}")
    private long expirationTime;

    private Key secretKey;

    @PostConstruct
    public void init() {
        byte[] apiSecretBytes = new byte[64];
        new SecureRandom().nextBytes(apiSecretBytes);
        secretKey = Keys.hmacShaKeyFor(apiSecretBytes);
    }

    public String generateToken(String username) {
        final User user = usuarioRepository.findByUsername(username);
        Map<String, Object> claims = new HashMap<>();
        
        claims.put("userid", user.getUserId());
        claims.put("username", username);
        claims.put("email", user.getEmail());
        claims.put("fullName", user.getFullName());
        claims.put("phone", user.getPhone());
        claims.put("date", user.getRegistrationDate());
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts
                .builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(Keys.hmacShaKeyFor(secretKey.getEncoded()))
                .compact();
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getEncoded()))
                .build()
                .parseSignedClaims(token).getPayload();
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
