package com.ecommerce.converter;

import com.ecommerce.entity.ProductEntity;
import com.ecommerce.model.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    private final ModelMapper modelMapper;

    public ProductEntity fromDTOToEntity(ProductDTO dto) {
        ProductEntity productEntity = modelMapper.map(dto, ProductEntity.class);
//        productEntity.setAttributeList();
        return productEntity;
    }

    public ProductDTO fromEntityToDTO(ProductEntity entity) {
        return modelMapper.map(entity, ProductDTO.class);
    }

}
