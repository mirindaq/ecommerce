package com.ecommerce.service;

import com.ecommerce.model.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO addOrUpdateProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProducts();
}
