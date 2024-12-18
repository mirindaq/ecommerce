package com.ecommerce.repository.custom;

import com.ecommerce.entity.ProductEntity;
import com.ecommerce.model.dto.ProductSearchCriteria;
import com.ecommerce.model.dto.ProductSearchCriteriaAdmin;

import java.util.List;

public interface ProductRepositoryCustom {
    List<ProductEntity> searchProduct(ProductSearchCriteria criteria);

    long countProducts(ProductSearchCriteria criteria);

    List<ProductEntity> searchProductAdmin(ProductSearchCriteriaAdmin criteria);

    long countProductsSearchAdmin(ProductSearchCriteriaAdmin criteria);
}
