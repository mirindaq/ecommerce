package com.ecommerce.repository;

import com.ecommerce.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository< ProductImageEntity, Long> {
    void deleteAllByProductId(Long productId);
}
