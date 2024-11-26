package com.ecommerce.service.impl;

import com.ecommerce.entity.BrandCategoryEntity;
import com.ecommerce.entity.BrandEntity;
import com.ecommerce.entity.CategoryEntity;
import com.ecommerce.model.dto.CategoryDTO;
import com.ecommerce.repository.BrandCategoryRepository;
import com.ecommerce.repository.BrandRepository;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ICategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final BrandCategoryRepository brandCategoryRepository;

    @Override
    @Transactional
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        CategoryEntity category = new CategoryEntity();
        category.setName(categoryDTO.getName());
        categoryRepository.save(category);

        if ( categoryDTO.getBrandsId() != null ){
            for ( Long brandId : categoryDTO.getBrandsId()) {
                BrandCategoryEntity brandCategory = new BrandCategoryEntity();
                brandCategory.setCategory(category);

                BrandEntity brand = brandRepository.findById(brandId)
                        .orElseThrow(() -> new RuntimeException("Khong tim thay brand"));

                brandCategory.setBrand(brand);
                brandCategoryRepository.save(brandCategory);
            }
        }

        return categoryDTO;
    }
}
