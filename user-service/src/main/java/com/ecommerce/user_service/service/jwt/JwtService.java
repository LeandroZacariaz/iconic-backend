package com.ecommerce.user_service.service.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String getToken(UserDetails user);

    String getEmailFromToken(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
