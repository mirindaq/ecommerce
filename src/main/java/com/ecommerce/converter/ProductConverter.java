package com.ecommerce.converter;

import com.ecommerce.entity.*;
import com.ecommerce.model.dto.ProductDTO;
import com.ecommerce.repository.AttributeDetailRepository;
import com.ecommerce.repository.AttributeRepository;
import com.ecommerce.repository.BrandRepository;
import com.ecommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final AttributeRepository attributeRepository;
    private final AttributeDetailRepository attributeDetailRepository;

    public ProductEntity fromDTOToEntity(ProductDTO dto) {
        ProductEntity productEntity = modelMapper.map(dto, ProductEntity.class);

        //set brand
        BrandEntity brand = brandRepository.findByName(dto.getBrandName())
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        productEntity.setBrand(brand);

        //set category
        CategoryEntity category = categoryRepository.findByName(dto.getCategoryName())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        productEntity.setCategory(category);
        productEntity.setActive(true);

        //set detail list
        List<AttributeDetailEntity> detailEntityList = new ArrayList<>();
        for (Map<String, String> map : dto.getAttributeList()) {
            String name = map.get("name");
            String value = map.get("value");

            //if attribute is not exist, new
            AttributeEntity attribute = attributeRepository.findByName(name)
                    .orElseGet(() -> {
                        AttributeEntity attributeEntity = new AttributeEntity();
                        attributeEntity.setName(name);
                        return attributeRepository.save(attributeEntity);
                    });

            //new attribute detail
            AttributeDetailEntity attributeDetailEntity = new AttributeDetailEntity();
            attributeDetailEntity.setValue(value);
            attributeDetailEntity.setAttribute(attribute);
            attributeDetailEntity.setProductAttribute(productEntity);

            detailEntityList.add(attributeDetailEntity);
        }
        productEntity.setAttributeList(detailEntityList);
        return productEntity;
    }

    public ProductDTO fromEntityToDTO(ProductEntity entity) {

        return modelMapper.map(entity, ProductDTO.class);
    }

}
