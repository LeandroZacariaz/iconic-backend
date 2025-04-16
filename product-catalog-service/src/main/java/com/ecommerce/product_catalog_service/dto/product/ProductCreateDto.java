package com.ecommerce.product_catalog_service.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "DTO para la creación de un producto")
public record ProductCreateDto(
    @NotNull(message = "The product name cannot be null.")
    @NotBlank(message = "The product name cannot be empty.")
    @Schema(description = "Nombre del producto", example = "Laptop XYZ")
    String name,
    @Schema(description = "Descripción del producto", example = "Una laptop de alto rendimiento.")
    String description,
    @Schema(description = "Imagen del producto")
    String image,
    @NotNull(message = "The price cannot be null.")
    @Positive(message = "The product price must be greater than zero.")
    @Schema(description = "Precio del producto", example = "999.99")
    Double price,
    @NotNull(message = "The stock cannot be null.")
    @PositiveOrZero(message = "The product stock must be zero or greater.")
    @Schema(description = "Cantidad en inventario", example = "50")
    Integer stock,
    @NotBlank(message = "The category name cannot be empty.")
    @Schema(description = "Nombre de la categoría del producto", example = "Electrónica")
    String name_category
) {

}
