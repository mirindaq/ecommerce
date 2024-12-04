package com.ecommerce.service.impl;

import com.ecommerce.converter.BrandConverter;
import com.ecommerce.entity.BrandCategoryEntity;
import com.ecommerce.entity.BrandEntity;
import com.ecommerce.entity.CategoryEntity;
import com.ecommerce.model.dto.BrandDTO;
import com.ecommerce.repository.BrandCategoryRepository;
import com.ecommerce.repository.BrandRepository;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IBrandService implements BrandService {

    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final BrandCategoryRepository brandCategoryRepository;


    @Override
    @Transactional
    public BrandDTO addOrUpdateBrand(BrandDTO brandDTO) {
        BrandEntity brandEntity;
        if (brandDTO.getId() != null) {
            brandEntity = brandRepository.findById(brandDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Brand not found"));
        } else {
            brandEntity = new BrandEntity();
        }

        brandEntity.setName(brandDTO.getName());
        brandEntity.setDescription(brandDTO.getDescription());

        brandRepository.save(brandEntity);
        if (brandDTO.getCategoriesId() != null) {
            brandCategoryRepository.deleteByBrandId(brandEntity.getId());

            for (Long categoryId : brandDTO.getCategoriesId()) {
                BrandCategoryEntity brandCategoryEntity = new BrandCategoryEntity();
                brandCategoryEntity.setBrand(brandEntity);

                CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("Category not found"));
                brandCategoryEntity.setCategory(categoryEntity);
                brandCategoryRepository.save(brandCategoryEntity);
            }
        }

        return brandDTO;
    }

    @Override
    public List<BrandDTO> getAllBrands() {
        List<BrandEntity> brandEntities = brandRepository.findAll();
        return brandEntities.stream()
                .map(brandEntity -> {
                    return BrandDTO.builder()
                            .id(brandEntity.getId())
                            .name(brandEntity.getName())
                            .description(brandEntity.getDescription())
                            .imageUrl(brandEntity.getImage())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public BrandDTO getBrandById(Long id) {
        return brandRepository.findById(id)
                .map(brandEntity -> BrandDTO.builder()
                        .id(brandEntity.getId())
                        .name(brandEntity.getName())
                        .description(brandEntity.getDescription())
                        .imageUrl(brandEntity.getImage())
                        .build())
                .orElseThrow(() -> new RuntimeException("Brand not found"));
    }

    @Override
    public List<BrandDTO> getBrandByCategory(String name) {
        return brandRepository.getBrandByCategoryName(name).stream()
                .map(brandEntity -> {
                    return BrandDTO.builder()
                            .id(brandEntity.getId())
                            .name(brandEntity.getName())
                            .description(brandEntity.getDescription())
                            .imageUrl(brandEntity.getImage())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
