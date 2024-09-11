package com.ecommerce.converter;


import com.ecommerce.entity.BrandEntity;
import com.ecommerce.model.dto.BrandDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrandConverter {

    private final ModelMapper modelMapper;

    private BrandEntity fromBrandDTOToEntity(BrandDTO brandDTO) {
        return modelMapper.map(brandDTO, BrandEntity.class);
    }

    private BrandDTO fromEntityToDTO(BrandEntity brandEntity) {
        return modelMapper.map(brandEntity, BrandDTO.class);
    }


}
