package com.ecommerce.converter;

import com.ecommerce.entity.CategoryEntity;
import com.ecommerce.model.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryConverter {

    private final ModelMapper modelMapper;

    public CategoryDTO fromEntityToDTO(CategoryEntity category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public CategoryEntity fromDTOToEntity(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, CategoryEntity.class);
    }

}
