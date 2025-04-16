package com.ecommerce.user_service.service.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JwtServiceImpl implements JwtService {

    private static final String SECRET_KEY = "A3F5E9D1C6B4A1D2F8E8C5E2A3C7D8B1A9D6E8F4B2C9D5F6E8D3A4C1E7F9B1";

    public String getToken(UserDetails user) {
      Map<String, Object> extraClaims = new HashMap<>();
      // Asumimos que el UserDetails tiene los roles como authorities
      String role = user.getAuthorities().stream()
              .map(authority -> authority.getAuthority().replace("ROLE_", "")) // Quitamos el prefijo "ROLE_"
              .findFirst()
              .orElse("USER"); // Rol por defecto si no hay authorities
      extraClaims.put("role", role);
      return getToken(extraClaims, user);
    }
   
    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
      try {
         return Jwts.builder().setClaims(extraClaims).setSubject(user.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 86400000L)).signWith(getKey(), SignatureAlgorithm.HS256).compact();
      } catch (Exception var4) {
         throw new JwtException("Error mientras se creaba el token: " + var4.getMessage());
      }
    }

    public String getRoleFromToken(String token) {
      return getClaim(token, claims -> claims.get("role", String.class));
    }
    
    private Key getKey() {
      byte[] keyBytes = (byte[])Decoders.BASE64.decode(SECRET_KEY);
      return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getEmailFromToken(String token) {
      return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
      String email = getEmailFromToken(token);
      return email.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private Claims getAllClaims(String token) {
      try {
         return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
      } catch (ExpiredJwtException var3) {
         throw new JwtException("Token Error: " + var3.getMessage());
      }
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
      Claims claims = getAllClaims(token);
      return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token) {
    return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
    return getExpiration(token).before(new Date());
    }

}
