package com.ecommerce.product_catalog_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.product_catalog_service.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
