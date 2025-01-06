package com.sapo.store_management.service;

import com.sapo.store_management.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String SERECT;

    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    @Autowired
    private UserService userService;

    // Generate Access Token
    public String generateAccessToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        boolean isAdmin = false;
        boolean isStaff = false;
        User user = userService.findByUsername(username);
        if (user != null && user.getRole() != null) {
            if (user.getRole().equals("ROLE_ADMIN")) {
                isAdmin = true;
            }
            if (user.getRole().equals("ROLE_STAFF")) {
                isStaff = true;
            }
        }
        claims.put("isAdmin", isAdmin);
        claims.put("isStaff", isStaff);
        return createToken(claims, username, accessTokenExpiration);
    }

    // Generate Refresh Token
    public String generateRefreshToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        boolean isAdmin = false;
        boolean isStaff = false;
        User user = userService.findByUsername(username);
        if (user != null && user.getRole() != null) {
            if (user.getRole().equals("ROLE_ADMIN")) {
                isAdmin = true;
            }
            if (user.getRole().equals("ROLE_STAFF")) {
                isStaff = true;
            }
        }
        claims.put("isAdmin", isAdmin);
        claims.put("isStaff", isStaff);
        return createToken(claims, username, refreshTokenExpiration);
    }


    private String createToken(Map<String, Object> claims, String username, long expirationTime) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, getSigneKey())
                .compact();
    }

    //lấy serect key
    private Key getSigneKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SERECT);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    // Extract all the information (claims) from a JWT token.
    public Claims extractAllClaims(String token) throws SignatureException {
        return Jwts.parserBuilder()
                .setSigningKey(getSigneKey()) // Đảm bảo `secretKey` chính xác
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    //Extract specific information from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    //Get the expiration time information from the token.
    public Date extractExpriration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //Retrieve the username information from the token.
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //Check if the token has expired
    private Boolean isTokenExpired(String token) {
        return extractExpriration(token).before(new Date());
    }

    // Validate Token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Map<String, Object> extractAllClaim(String token) {
        try {
            // Sử dụng Jwts parser để giải mã token
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigneKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        } catch (SignatureException e) {
            throw new RuntimeException("JWT signature validation failed");
        }
    }


    // Validate Refresh Token and generate new Access Token
    public String validateAndGenerateAccessToken(String refreshToken, UserDetails userDetails) {
        if (validateToken(refreshToken, userDetails)) {
            return generateAccessToken(userDetails.getUsername());
        } else {
            throw new RuntimeException("Invalid or expired refresh token");
        }
    }


}
