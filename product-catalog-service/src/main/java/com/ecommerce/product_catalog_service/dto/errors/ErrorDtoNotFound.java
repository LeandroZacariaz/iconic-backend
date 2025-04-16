package com.ecommerce.product_catalog_service.dto.errors;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para errores cuando un recurso no es encontrado")
public record ErrorDtoNotFound(
    @Schema(description = "Ruta de la solicitud donde ocurrió el error")
    String path,
    
    @Schema(description = "Mensaje detallado del error")
    String message
) {

}
