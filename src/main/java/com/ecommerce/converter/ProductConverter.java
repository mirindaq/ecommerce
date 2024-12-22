package com.ecommerce.converter;

import com.ecommerce.entity.*;
import com.ecommerce.model.dto.AttributeDTO;
import com.ecommerce.model.dto.ProductAddDTO;
import com.ecommerce.model.dto.ProductDTO;
import com.ecommerce.repository.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final AttributeRepository attributeRepository;
    private final ProductRepository productRepository;

    public ProductEntity fromAddDTOToEntity(ProductAddDTO dto) {
        ProductEntity productEntity;

        if ( dto.getId() == null ){
            productEntity = modelMapper.map(dto, ProductEntity.class);
        }else {
            productEntity = productRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Product not found"));
            productEntity.setDescription(dto.getDescription());
            productEntity.setPrice(dto.getPrice());
            productEntity.setDiscount(dto.getDiscount());
            productEntity.setName(dto.getName());
            productEntity.setStock(dto.getStock());
        }

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
        for ( AttributeDTO map : dto.getAttributes()) {
            String name = map.getName();
            String value = map.getValue();

            AttributeEntity attribute = attributeRepository.findByNameAndCategory_Name(name, dto.getCategoryName())
                    .orElseThrow(() -> new RuntimeException("Attribute not found"));

            AttributeDetailEntity attributeDetailEntity = new AttributeDetailEntity();
            attributeDetailEntity.setValue(value);
            attributeDetailEntity.setAttribute(attribute);
            attributeDetailEntity.setProduct(productEntity);

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
            List<AttributeDTO> attributes = entity.getAttributeList().stream()
                    .map(attributeDetail -> {
                        AttributeDTO attributeDTO = new AttributeDTO();
                        attributeDTO.setName(attributeDetail.getAttribute().getName());
                        attributeDTO.setValue(attributeDetail.getValue()); // Cài đặt value từ chi tiết thuộc tính
                        return attributeDTO; // Trả về đối tượng AttributeDTO
                    })
                    .collect(Collectors.toList()); // Thu thập kết quả thành danh sách
            productDTO.setAttributeList(attributes); // Đặt vào list thuộc tính trong ProductDTO
        }


        return productDTO;
    }


}
