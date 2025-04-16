package com.ecommerce.user_service.dto.user;

import com.ecommerce.user_service.domain.enums.RoleEnumUser;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información de un usuario en el sistema")
public record UserDto(
    @Schema(description = "ID único del usuario", example = "10")
    Long id_user,
    @Schema(description = "Username del usuario", example = "JuanPérez")
    String username,
    @Schema(description = "Correo electrónico del usuario", example = "juan.perez@email.com")
    String email,
    @Schema(description = "Rol del usuario en el sistema", example = "USER")
    RoleEnumUser role) {

}
