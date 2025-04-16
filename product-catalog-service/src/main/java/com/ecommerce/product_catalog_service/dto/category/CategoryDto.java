package com.ecommerce.product_catalog_service.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para representar una categoría sin usar la entidad directamente")
public record CategoryDto(
    @Schema(description = "Identificador único de la categoría", example = "1")
    Long id_category,
    @Schema(description = "Nombre de la categoría", example = "Electrónica")
    String name
) {

}
