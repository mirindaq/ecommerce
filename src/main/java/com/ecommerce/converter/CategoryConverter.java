package com.ecommerce.converter;

import com.ecommerce.entity.AttributeEntity;
import com.ecommerce.entity.CategoryEntity;
import com.ecommerce.model.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryConverter {

    public CategoryDTO fromEntityToDTO(CategoryEntity category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        categoryDTO.setImage(category.getImage());
        categoryDTO.setListAttribute(category.getAttributeEntities().
                stream().map(AttributeEntity::getName).collect(Collectors.toList()));
        return categoryDTO;
    }
}
