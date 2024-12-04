package com.ecommerce.service.impl;

import com.ecommerce.converter.ProductConverter;
import com.ecommerce.entity.AttributeDetailEntity;
import com.ecommerce.entity.AttributeEntity;
import com.ecommerce.entity.BrandEntity;
import com.ecommerce.entity.ProductEntity;
import com.ecommerce.model.dto.ProductDTO;
import com.ecommerce.model.dto.ProductSearchCriteria;
import com.ecommerce.model.response.ProductResponse;
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

    @Override
    public ProductResponse getAllProducts() {
        ProductResponse response = new ProductResponse();
        response.setProducts(productRepository.findAll().stream().map(productConverter::fromEntityToDTO).collect(Collectors.toList()));
        return response ;
    }

    @Override
    public ProductResponse searchProducts(ProductSearchCriteria criteria) {
        List<ProductEntity> entityList = productRepository.searchProduct(criteria);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProducts(entityList.stream().map(productConverter::fromEntityToDTO).collect(Collectors.toList()));
        productResponse.setPage(criteria.getPage());
        productResponse.setLimit(criteria.getLimit());
        productResponse.setTotal(productRepository.countProducts(criteria));
        return productResponse;
    }

    @Override
    public ProductDTO getProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return productConverter.fromEntityToDTO(productEntity);
    }
}
