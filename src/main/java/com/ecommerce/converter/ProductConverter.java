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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        //set imageList
        List<ProductImageEntity> images = new ArrayList<>();
        int check = 0;
        for ( String image : dto.getImages()) {
            ProductImageEntity imageEntity = new ProductImageEntity();
            imageEntity.setUrl(image);
            imageEntity.setProduct(productEntity);
            if ( check == 0 ) {
                imageEntity.setMain(true);
                check = 1;
            }
            else imageEntity.setMain(false);
            images.add(imageEntity);
        }
        productEntity.setProductImageEntityList(images);

        //set detail list
        List<AttributeDetailEntity> detailEntityList = new ArrayList<>();
        for (Map<String, String> map : dto.getAttributeList()) {
            String name = map.get("name");
            String value = map.get("value");

            AttributeEntity attribute = attributeRepository.findByName(name)
                    .orElseThrow(() -> new RuntimeException("Attribute not found"));

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
        ProductDTO productDTO = new ProductDTO();

        productDTO.setCreatedBy(entity.getCreatedBy());
        productDTO.setCreatedDate(entity.getCreatedDate());
        productDTO.setModifiedBy(entity.getModifiedBy());
        productDTO.setModifiedDate(entity.getModifiedDate());
        productDTO.setId(entity.getId());
        productDTO.setName(entity.getName());
        productDTO.setPrice(entity.getPrice());
        productDTO.setStock(entity.getStock());
        productDTO.setDiscount(entity.getDiscount());
        productDTO.setDescription(entity.getDescription());
        productDTO.setImages(entity.getProductImageEntityList().stream().map(ProductImageEntity::getUrl).collect(Collectors.toList()));

        if (entity.getBrand() != null) {
            productDTO.setBrandName(entity.getBrand().getName());
        }

        if (entity.getCategory() != null) {
            productDTO.setCategoryName(entity.getCategory().getName());
        }

        if (entity.getAttributeList() != null) {
            List<Map<String, String>> attributes = entity.getAttributeList().stream()
                    .map(attributeDetail -> {
                        Map<String, String> attributeMap = Map.of(
                                "name", attributeDetail.getAttribute().getName(),
                                "value", attributeDetail.getValue()
                        );
                        return attributeMap;
                    })
                    .collect(Collectors.toList());
            productDTO.setAttributeList(attributes);
        }

        return productDTO;
    }


}
