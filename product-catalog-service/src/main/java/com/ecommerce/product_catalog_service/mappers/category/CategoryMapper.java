package com.ecommerce.product_catalog_service.mappers.category;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ecommerce.product_catalog_service.domain.Category;
import com.ecommerce.product_catalog_service.dto.category.CategoryCreateDto;
import com.ecommerce.product_catalog_service.dto.category.CategoryDto;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id_category", ignore = true)
    @Mapping(target = "products", ignore = true)
    Category categoryCreateDtoToCategory(CategoryCreateDto categoryDto);

    CategoryDto categoryToCategoryDto(Category category);
}
