package com.ecommerce.service;

import com.ecommerce.model.dto.BrandDTO;

import java.util.List;

public interface BrandService {

    BrandDTO addOrUpdateBrand(BrandDTO brandDTO);

    List<BrandDTO> getAllBrands();

    BrandDTO getBrandById(Long id);

    List<BrandDTO> getBrandByCategory(String name);
}
