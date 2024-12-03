package com.ecommerce.service;

import com.ecommerce.model.dto.ProductDTO;
import com.ecommerce.model.dto.ProductSearchCriteria;

import java.util.List;

public interface ProductService {

    ProductDTO addOrUpdateProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProducts();

    List<ProductDTO> searchProducts(ProductSearchCriteria criteria);

    ProductDTO getProductById(Long id);
}
