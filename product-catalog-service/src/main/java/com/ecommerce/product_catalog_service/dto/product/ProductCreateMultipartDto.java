package com.ecommerce.product_catalog_service.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.web.multipart.MultipartFile;

public record ProductCreateMultipartDto(
    @NotBlank(message = "El nombre es obligatorio")
    String name,

    String description,

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que 0")
    Double price,

    @NotNull(message = "El stock es obligatorio")
    @PositiveOrZero(message = "El stock no puede ser negativo")
    Integer stock,

    @NotBlank(message = "La categoría es obligatoria")
    String name_category,

    MultipartFile image
) {
    
}
