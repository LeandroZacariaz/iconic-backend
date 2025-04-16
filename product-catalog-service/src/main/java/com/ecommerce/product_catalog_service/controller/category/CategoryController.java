package com.ecommerce.product_catalog_service.controller.category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.product_catalog_service.dto.category.CategoryCreateDto;
import com.ecommerce.product_catalog_service.service.category.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RequestMapping("/category")
@RestController
@AllArgsConstructor
@Tag(name = "Categorías", description = "Operaciones relacionadas con las categorías.")
public class CategoryController {

    private CategoryService categoryService;

    @Operation(summary = "Crear una nueva categoría", description = "Crea una categoría con el nombre especificado")
    @ApiResponse(responseCode = "201", description = "Categoría creada exitosamente")
    @PostMapping()
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryCreateDto categoryCreateDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryCreateDto));
    }

    @Operation(summary = "Obtener todas las categorías", description = "Devuelve una lista de todas las categorías existentes")
    @ApiResponse(responseCode = "200", description = "Lista de categorías obtenida exitosamente")
    @GetMapping()
    public ResponseEntity<?> getAllCategories(){
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }

    @Operation(summary = "Obtener categoría por ID", description = "Devuelve los detalles de una categoría por ID.")
    @ApiResponse(responseCode = "200", description = "Categoría obtenida exitosamente")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id_category){
        return ResponseEntity.ok().body(categoryService.getCategoryById(id_category));
    }

    @Operation(summary = "Eliminar una categoría", description = "Elimina una categoría específica por su ID")
    @ApiResponse(responseCode = "204", description = "Categoría eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id_category){
        categoryService.deleteCategory(id_category);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Actualizar una categoría", description = "Actualiza el contenido de una categoría existente por ID")
    @ApiResponse(responseCode = "200", description = "Categoría actualizada exitosamente")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") Long id_category, @RequestBody @Valid CategoryCreateDto categoryCreateDto){
        return ResponseEntity.ok().body(categoryService.updateCategory(id_category, categoryCreateDto));
    }
}
