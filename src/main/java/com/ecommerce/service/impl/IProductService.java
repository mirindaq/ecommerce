package com.ecommerce.service.impl;

import com.ecommerce.converter.ProductConverter;
import com.ecommerce.entity.AttributeDetailEntity;
import com.ecommerce.entity.AttributeEntity;
import com.ecommerce.entity.BrandEntity;
import com.ecommerce.entity.ProductEntity;
import com.ecommerce.model.dto.ProductDTO;
import com.ecommerce.repository.AttributeDetailRepository;
import com.ecommerce.repository.AttributeRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IProductService implements ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final AttributeRepository attributeRepository;
    private final AttributeDetailRepository attributeDetailRepository;

    @Override
    @Transactional
    public ProductDTO addOrUpdateProduct(ProductDTO productDTO) {
        ProductEntity productEntity = productConverter.fromDTOToEntity(productDTO);
        productRepository.save(productEntity);
        return productDTO;

    }
}
