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

    @Value("${jwt.expiration}")
    private long Expiration;

    @Autowired
    private UserService userService;

    public String generateToken(String username) {
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
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Expiration))
                .signWith(SignatureAlgorithm.HS512, getSigneKey())
                .compact();
    }

    //lấy serect key
    private Key getSigneKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SERECT);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    // Extract all the information (claims) from a JWT token.
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSigneKey()).parseClaimsJws(token).getBody();
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

    //Check the validity of the token and match it with user information.
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String tenDangNhap = extractUsername(token);
        System.out.println(tenDangNhap);
        return (tenDangNhap.equals(userDetails.getUsername()) && !isTokenExpired(token));
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


}
