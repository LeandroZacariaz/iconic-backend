package com.ecommerce.product_catalog_service.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para representar un producto sin usar la entidad directamente")
public record ProductDto(
    @Schema(description = "Identificador único del producto", example = "1")
    Long id_product,
    @Schema(description = "Nombre del producto", example = "Laptop XYZ")
    String name,
    @Schema(description = "Descripción del producto", example = "Una laptop de alto rendimiento.")
    String description,
    @Schema(description = "Imagen del producto")
    String image,
    @Schema(description = "Precio del producto", example = "999.99")
    Double price,
    @Schema(description = "Cantidad en inventario", example = "50")
    Integer stock,
    @Schema(description = "Nombre de la categoría del producto", example = "Ropa de Hombre")
    String categoryName
) {

}
