package com.ecommerce.product_catalog_service.service.jwt;

public interface JwtService {
    String getEmailFromToken(String token);

    boolean isTokenValid(String token, String email);

    String getRoleFromToken(String token);
}
