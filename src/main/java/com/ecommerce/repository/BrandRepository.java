package com.ecommerce.repository;

import com.ecommerce.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    Optional<BrandEntity> findByName(String brandName);

//    List<BrandEntity>  findAllBy();
}
