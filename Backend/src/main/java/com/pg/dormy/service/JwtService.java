package com.pg.dormy.service;

import com.pg.dormy.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

@Service
public class JwtService {
    private final String SECRET_KEY = "80171f64c835a536f182d01eac1579a2e2bb80bf637144c19f7b8c572ca0fb10";

    public  String generateToken(User user){
        String token = Jwts
                .builder()
                .subject(user.getPhoneNumber())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ 24 * 60 * 60 * 1000))
                .signWith(getSigninkey())
                .compact();
        return token;
    }
    private SecretKey getSigninkey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);

    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .verifyWith(getSigninkey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T extractClaim(String token, Function<Claims,T> resolver){
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    public String extractUserPhone(String token){
        return extractClaim(token,Claims::getSubject);
    }

    public boolean isValid(String token, User user){
        String phone = extractUserPhone(token);
        return phone.equals(user.getPhoneNumber()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }
}
