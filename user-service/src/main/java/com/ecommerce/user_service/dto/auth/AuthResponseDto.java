package com.ecommerce.user_service.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "Respuesta de autenticación con token JWT")
@Builder
public record AuthResponseDto(
    @Schema(description = "Token de acceso JWT") 
    String token) {

}
