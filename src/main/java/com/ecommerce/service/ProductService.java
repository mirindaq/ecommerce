package com.ecommerce.service;

import com.ecommerce.model.dto.ProductAddDTO;
import com.ecommerce.model.dto.ProductDTO;
import com.ecommerce.model.dto.ProductSearchCriteria;
import com.ecommerce.model.dto.ProductSearchCriteriaAdmin;
import com.ecommerce.model.response.ProductResponse;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    ProductDTO addOrUpdateProduct(ProductAddDTO productDTO);

    ProductResponse getAllProducts();

    ProductResponse searchProducts(ProductSearchCriteria criteria);

    ProductResponse searchProductsByAdmin(ProductSearchCriteriaAdmin criteria);

    ProductDTO getProductById(Long id);
}
