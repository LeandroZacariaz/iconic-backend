package com.ecommerce.product_catalog_service.service.product;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.stereotype.Service;

import com.ecommerce.product_catalog_service.domain.Product;
import com.ecommerce.product_catalog_service.dto.product.ProductCreateDto;
import com.ecommerce.product_catalog_service.dto.product.ProductCreateMultipartDto;
import com.ecommerce.product_catalog_service.dto.product.ProductDto;
import com.ecommerce.product_catalog_service.exceptions.ResourceNotFoundException;
import com.ecommerce.product_catalog_service.mappers.product.ProductMapper;
import com.ecommerce.product_catalog_service.repository.CategoryRepository;
import com.ecommerce.product_catalog_service.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductMapper productMapper;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Override
    public ProductDto createProduct(ProductCreateMultipartDto productCreateDto) {
        Product product = new Product();
        product.setName(productCreateDto.name());
        product.setDescription(productCreateDto.description());
        product.setPrice(productCreateDto.price());
        product.setStock(productCreateDto.stock());
        product.setCategory(categoryRepository.findByName(productCreateDto.name_category())
            .orElseThrow(() -> new ResourceNotFoundException("La categoría con nombre: " + productCreateDto.name_category() + " no existe.")));

        // Manejar la imagen
        if (productCreateDto.image() != null && !productCreateDto.image().isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + productCreateDto.image().getOriginalFilename();
                // Usar una carpeta externa para evitar problemas con el classpath
                Path filePath = Paths.get("static/images/" + fileName);
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, productCreateDto.image().getBytes());
                product.setImage("/images/" + fileName);
            } catch (Exception e) {
                throw new RuntimeException("Error al guardar la imagen: " + e.getMessage());
            }
        }

        Product savedProduct = productRepository.save(product);
        return productMapper.productToProductDto(savedProduct);
    }
    
    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::productToProductDto).toList();
    }

    @Override
    public ProductDto getProductById(Long id) {
        return productRepository.findById(id).map(productMapper::productToProductDto)
                                .orElseThrow(() -> 
                                new ResourceNotFoundException("El producto con ID: " + id + " no existe."));
    }

    @Override
    public ProductDto updateProduct(Long id_product, ProductCreateMultipartDto productCreateDto) {
        // Buscar el producto existente
        Product product = productRepository.findById(id_product)
            .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id_product));

        // Actualizar los campos del producto
        product.setName(productCreateDto.name());
        product.setDescription(productCreateDto.description());
        product.setPrice(productCreateDto.price());
        product.setStock(productCreateDto.stock());
        product.setCategory(categoryRepository.findByName(productCreateDto.name_category())
            .orElseThrow(() -> new ResourceNotFoundException("La categoría con nombre: " + productCreateDto.name_category() + " no existe.")));

        // Manejar la imagen
        if (productCreateDto.image() != null && !productCreateDto.image().isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + productCreateDto.image().getOriginalFilename();
                // Usar una carpeta externa para evitar problemas con el classpath
                Path filePath = Paths.get("static/images/" + fileName);
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, productCreateDto.image().getBytes());
                product.setImage("/images/" + fileName);
            } catch (Exception e) {
                throw new RuntimeException("Error al guardar la imagen: " + e.getMessage());
            }
        }

        // Guardar el producto actualizado
        Product updatedProduct = productRepository.save(product);
        return productMapper.productToProductDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("El producto con ID: " +id+" no existe." );
        }
    }

}
