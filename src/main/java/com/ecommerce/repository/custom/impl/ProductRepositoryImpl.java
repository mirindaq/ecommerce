package com.ecommerce.repository.custom.impl;

import com.ecommerce.entity.ProductEntity;
import com.ecommerce.model.dto.ProductSearchCriteria;
import com.ecommerce.repository.custom.ProductRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ProductEntity> searchProduct(ProductSearchCriteria criteria) {
        String name = criteria.getName();
        Double minPrice = criteria.getMinPrice();
        Double maxPrice = criteria.getMaxPrice();
        Double discount = criteria.getDiscount();
        String brandName = criteria.getBrandName();
        String categoryName = criteria.getCategoryName();

        List<String> conditions = new ArrayList<>();
        String query = "SELECT p FROM ProductEntity p WHERE 1=1 ";

        if (name != null && !name.isEmpty()) {
            query += " AND p.name LIKE :name";
            conditions.add("name");
        }
        if (minPrice != null) {
            query += " AND p.price >= :minPrice";
            conditions.add("minPrice");
        }

        if (maxPrice != null) {
            query += " AND p.price <= :maxPrice";
            conditions.add("maxPrice");
        }

        if (discount != null) {
            query += " AND p.discount >= :discount";
            conditions.add("discount");
        }
        if (brandName != null && !brandName.isEmpty()) {
            query += " AND LOWER(p.brand.name) = LOWER(:brandName)";
            conditions.add("brandName");
        }

        if (categoryName != null && !categoryName.isEmpty()) {
            query += " AND LOWER(p.category.name) = LOWER(:categoryName)";
            conditions.add("categoryName");
        }

        TypedQuery<ProductEntity> typedQuery = em.createQuery(query, ProductEntity.class);

        if (conditions.contains("name")) typedQuery.setParameter("name", "%" + name + "%");
        if (conditions.contains("minPrice") && minPrice != null) {
            typedQuery.setParameter("minPrice", minPrice);
        }
        if (conditions.contains("maxPrice") && maxPrice != null) {
            typedQuery.setParameter("maxPrice", maxPrice);
        }
        if (conditions.contains("discount")) typedQuery.setParameter("discount", discount);
        if (conditions.contains("brandName")) typedQuery.setParameter("brandName", brandName);
        if (conditions.contains("categoryName")) typedQuery.setParameter("categoryName", categoryName);

        return typedQuery.getResultList();
    }
}
