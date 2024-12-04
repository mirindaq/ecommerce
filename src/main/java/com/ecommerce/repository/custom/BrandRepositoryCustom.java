package com.ecommerce.repository.custom;

import com.ecommerce.entity.BrandEntity;

import java.util.List;

public interface BrandRepositoryCustom {
    List<BrandEntity> getBrandByCategoryName(String categoryName);
}
