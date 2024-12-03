package com.ecommerce.service.impl;

import com.ecommerce.converter.CategoryConverter;
import com.ecommerce.entity.AttributeEntity;
import com.ecommerce.entity.CategoryEntity;
import com.ecommerce.model.dto.CategoryDTO;
import com.ecommerce.repository.AttributeRepository;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ICategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final AttributeRepository attributeRepository;
    private final CategoryConverter categoryConverter;

    @Override
    @Transactional
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        CategoryEntity category = new CategoryEntity();
        category.setName(categoryDTO.getName());
        category.setImage(categoryDTO.getImage());
        categoryRepository.save(category);

        for ( String x : categoryDTO.getListAttribute() ){
            AttributeEntity attribute = new AttributeEntity();
            attribute.setName(x);
            attribute.setCategory(category);
            attributeRepository.save(attribute);
        }
        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<CategoryEntity> categoryList = categoryRepository.findAll();
        return categoryList.stream()
                .map(categoryConverter::fromEntityToDTO)
                .collect(Collectors.toList());
    }
}
