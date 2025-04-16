package com.ecommerce.product_catalog_service.config;

import org.springframework.http.HttpHeaders;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
    info = @Info(
        title = "Product Catalog Service API - Scalable Ecommerce Platform",
        description = "API REST para la gestión del catálogo de productos en la plataforma de comercio electrónico escalable. Este microservicio permite consultar, crear y actualizar productos y categorías.",
        version = "1.0",
        contact = @Contact(
            name = "Leandro Zacariaz",
            email = "leandrozacariaz18@gmail.com",
            url = "https://github.com/LeandroZacariaz"
        ),
        license = @License(
            name = "Unlicensed",
            url = "https://choosealicense.com/no-permission/"
        )
    ),
    security = @SecurityRequirement(
        name = "Security Token"
    )
)
@SecurityScheme(
    name = "Security Token",
    description = "Token de acceso JWT para autenticación en el microservicio Product Catalog Service",
    type = SecuritySchemeType.HTTP,
    paramName = HttpHeaders.AUTHORIZATION,
    in = SecuritySchemeIn.HEADER,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class SwaggerConfig {

}
