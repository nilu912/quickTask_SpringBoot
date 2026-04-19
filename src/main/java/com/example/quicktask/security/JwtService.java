package com.example.quicktask.security;

import com.example.quicktask.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {

    @Value("${jwt.secretkey}")
    private String SECRET;

    @Value("${jwt.expiration}")
    private long expirationTime;

    // converting our secrete into cryptographic key Object
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    // Generate a token for a user
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())        //we use the same key which we use at time of creating token
                .build()
                .parseClaimsJwt(token)     // verify signature + parse
                .getBody();             //Give me the payload as Claims object
    }

    // Generic extractor - which takes function that picks one claim from all claims
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract email - passes a functions that picks "sub" from claims
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract expiry
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Check if token expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Data());
    }

    // Final Validation - is email matching + not expired?
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String emailFromToken = extractEmail(token);
        return emailFromToken.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }


//    public String isTokenValid(String token, UserRepository user) {
//        String email = extractEmail(token);
//        return email.equals()
//    }
}
