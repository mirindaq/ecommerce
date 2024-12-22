package com.ecommerce.repository;

import com.ecommerce.entity.AttributeDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeDetailRepository extends JpaRepository<AttributeDetailEntity, Long> {
    void deleteAllByProduct_Id(Long productId);
}
