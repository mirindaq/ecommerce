package com.ecommerce.repository;

import com.ecommerce.entity.BrandEntity;
import com.ecommerce.repository.custom.BrandRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> , BrandRepositoryCustom {
    Optional<BrandEntity> findByName(String brandName);

//    List<BrandEntity>  findAllBy();
}
