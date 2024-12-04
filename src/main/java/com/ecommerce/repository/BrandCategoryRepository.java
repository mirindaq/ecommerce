package com.ecommerce.repository;

import com.ecommerce.entity.BrandCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandCategoryRepository extends JpaRepository<BrandCategoryEntity, Long> {
    void deleteByBrandId(long brandId);

}
