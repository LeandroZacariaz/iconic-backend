
# Scalable Ecommerce Platform

API RESTful basada en una arquitectura de microservicios y contenedores Docker. Cada funcionalidad clave del sistema se implementa como un microservicio independiente, permitiendo desarrollo, despliegue y escalado autónomos.

Actualmente, la plataforma incluye tres microservicios principales: User Service, Product Catalog Service y Shopping Cart Service. La idea es seguir integrando servicios como gestión de órdenes, procesamiento de pagos y notificaciones.

## Características
#### User Service
- Registro y autenticación de usuarios mediante JWT.
- Gestión de perfiles de usuario.
#### Product Catalog Service
- Creación, actualización y eliminación de productos y categorías (operaciones CRUD).
#### Shopping Cart Service
- Añadir/eliminar items al carrito.
- Obtener carrito del usuario.
#### General
- Comunicación entre servicios mediante APIs RESTful.
- Contenerización con Docker para despliegue simplificado.
- Documentación de APIs con Swagger (por microservicio)
#### Futuras Características (en desarrollo)
- Procesamiento de órdenes y pagos.
- Notificaciones por email/SMS.
- Resumen de estadísticas de compras.


## Tecnologías Utilizadas
- Java 21

- Spring Boot 3.3.5 (por microservicio)

- Spring Security

- JWT

- Maven

- Docker

- Docker Compose (para desarrollo local)

- MySQL

- Swagger


## Dependencias 📦

| Dependencia | Versión     |
| :--------   | :-------    |
| Springdoc OpenAPI Starter WebMvc UI  | 2.5.0    | 
| Spring Boot Starter Web   | 3.3.5    |
| Spring Boot Starter Validation  | 3.3.5    |
| Spring Boot Starter Security| 3.3.5 |
| Spring Boot Starter Data JPA   | 3.3.5   |
| Spring Boot DevTools   | 3.3.5    |
| Spring Boot Starter Test | 3.3.5 |
| JWT | 0.11.5 |
| Lombok   | 1.18.34    |
| MySQL Connector-J   | 8.3.0    |
| MapStruct  | 1.5.5   |
| MapStruct Processor    | 1.5.5.Final    |


## Instalación

### Prerrequisitos

Asegúrate de tener instalados los siguientes programas:

- [Maven](https://maven.apache.org/install.html)
- [Docker](https://www.docker.com/get-started/)
- Docker Compose 


## Autor

- Zacariaz, Leandro - [Linkedin](https://www.linkedin.com/in/leandro-zacariaz-39b47a323/)

