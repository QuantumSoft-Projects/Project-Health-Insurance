package com.quantumsoft.insurance.serviceimpl;

import com.quantumsoft.insurance.entity.Admin;

import com.quantumsoft.insurance.entity.SuperAdmin;

import com.quantumsoft.insurance.entity.Users;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.JwtException;

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

import java.util.function.Function;

@Service

public class JwtService {

    // Any text you can use for the secret

    public static final String SECRET = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";

    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);

    }

    public Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);

    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);

    }

    public String extractRole(String token) {

        Claims allClaims = extractAllClaims(token);

        String role = allClaims.get("Role", String.class);

        System.out.println(role + "*************Role");

        return role;

    }

    private Claims extractAllClaims(String token) {

        try {

            return Jwts

                    .parserBuilder()

                    .setSigningKey(getSignKey())

                    .setAllowedClockSkewSeconds(300) // Allows 5 minutes clock skew

                    .build()

                    .parseClaimsJws(token)

                    .getBody();

        } catch (IllegalArgumentException e) {

            throw new RuntimeException("Invalid Token: " + e);

        }

    }


    private Boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());

    }

    public Boolean validateToken(String token, UserDetails userDetails) {

        try {

            Jwts.parserBuilder()

                    .setSigningKey(getSignKey())

                    .build()

                    .parseClaimsJws(token);

            final String username = extractUsername(token);

            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

        }catch(JwtException | IllegalArgumentException e) {

            throw new RuntimeException("Invalid Token" + e);

        }

    }

    public Boolean validateToken(String token) {

        try {

            Jwts.parserBuilder()

                    .setSigningKey(getSignKey())

                    .build()

                    .parseClaimsJws(token);

            return (!isTokenExpired(token));

        }catch(JwtException | IllegalArgumentException e) {

            throw new RuntimeException("Invalid Token" + e);

        }

    }

//    public String generateToken(String username, Admin admin){

//        Map<String, Object> claims = new HashMap<>();

//        //claims.put("Password", admin.getId());

//        // claims.put("Address", userEntity.getAddress());

//        claims.put("Username", username);

//        claims.put("Role", admin.getRole().name());

//        return createToken(claims, username);

//    }

    public String generateToken(String username, SuperAdmin superAdmin){

        Map<String, Object> claims = new HashMap<>();

        //claims.put("Password", admin.getId());

        // claims.put("Address", userEntity.getAddress());

        claims.put("Username", username);

        claims.put("Role", superAdmin.getRole().name());

        return createToken(claims, username);

    }

    public String generateToken(Admin admin){

        Map<String, Object> claims = new HashMap<>();

        claims.put("Email", admin.getEmail());

        claims.put("Role", admin.getRole().name());

        return createToken(claims, admin.getEmail());

    }

    public String generateToken(Users user){

        Map<String, Object> claims = new HashMap<>();

        claims.put("Email", user.getEmail());

        claims.put("Role", user.getRole().name());

        return createToken(claims, user.getEmail());

    }

    public String generateToken(String email){

        Map<String, Object> claims = new HashMap<>();

        claims.put("email", email);

        return Jwts.builder()

                .setClaims(claims)

                .setIssuedAt(new Date(System.currentTimeMillis()))

                .setExpiration(new Date(System.currentTimeMillis()+1000*60*1))

                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();

    }

    private String createToken(Map<String, Object> claims, String username) {

        return Jwts.builder()

                .setClaims(claims)

                .setSubject(username)

                .setIssuedAt(new Date(System.currentTimeMillis()))

                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour

                .signWith(getSignKey(), SignatureAlgorithm.HS256)

                .compact();

    }

    private Key getSignKey() {

        byte[] keyBytes = Decoders.BASE64.decode(SECRET);

        return Keys.hmacShaKeyFor(keyBytes);

    }

//    public String generateToken(Admin admin){

//        Map<String, Object> claims = new HashMap<>();

//        claims.put("username", admin.getUsername());

//        claims.put("role", admin.getRole());

//        return createToken(claims);

//    }

//

//    public String generateToken() {

//        Map<String, Object> claims = new HashMap<>();

//        claims.put("Role", "CUSTOMER");

//        return createToken(claims);

//    }

    private String createToken(Map<String, Object> claims) {

        return Jwts.builder()

                .setClaims(claims)

                .setIssuedAt(new Date(System.currentTimeMillis()))

                .setExpiration(new Date(System.currentTimeMillis()+1000*60*1))

                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();

    }

}

