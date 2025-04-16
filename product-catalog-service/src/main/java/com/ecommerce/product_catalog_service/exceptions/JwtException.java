package com.ecommerce.product_catalog_service.exceptions;

public class JwtException extends RuntimeException {
    public JwtException(String message) {
       super(message);
    }
 }
 