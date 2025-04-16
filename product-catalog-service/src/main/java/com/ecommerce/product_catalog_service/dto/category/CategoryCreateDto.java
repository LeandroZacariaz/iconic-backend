package com.ecommerce.product_catalog_service.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO para la creación de una categoría")
public record CategoryCreateDto(
    @NotNull(message = "The category name cannot be null.")
    @NotBlank(message = "The category name cannot be empty.")
    @Schema(description = "Nombre de la categoría", example = "Electrónica")
    String name
) {

}
