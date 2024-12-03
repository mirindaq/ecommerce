package com.ecommerce.repository;

import com.ecommerce.entity.ProductEntity;
import com.ecommerce.repository.custom.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>, ProductRepositoryCustom {
}
