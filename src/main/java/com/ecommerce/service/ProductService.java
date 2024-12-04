package com.ecommerce.service;

import com.ecommerce.model.dto.ProductDTO;
import com.ecommerce.model.dto.ProductSearchCriteria;
import com.ecommerce.model.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductDTO addOrUpdateProduct(ProductDTO productDTO);

    ProductResponse getAllProducts();

    ProductResponse searchProducts(ProductSearchCriteria criteria);

    ProductDTO getProductById(Long id);
}
