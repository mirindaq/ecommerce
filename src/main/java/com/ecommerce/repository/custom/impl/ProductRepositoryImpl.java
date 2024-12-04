package com.ecommerce.repository.custom.impl;

import com.ecommerce.entity.ProductEntity;
import com.ecommerce.model.dto.ProductSearchCriteria;
import com.ecommerce.repository.custom.ProductRepositoryCustom;
import com.ecommerce.utils.StringUtils;
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
        String query = "SELECT p FROM ProductEntity p WHERE 1=1 ";

        if (StringUtils.isNotBlank(criteria.getName())) {
            query += " AND p.name LIKE :name";
        }
        if (criteria.getMinPrice() != null) {
            query += " AND p.price >= :minPrice";
        }
        if (criteria.getMaxPrice() != null) {
            query += " AND p.price <= :maxPrice";
        }
        if (criteria.getDiscount() != null) {
            query += " AND p.discount >= :discount";
        }
        if (StringUtils.isNotBlank(criteria.getBrandName())) {
            query += " AND LOWER(p.brand.name) = LOWER(:brandName)";
        }
        if (StringUtils.isNotBlank(criteria.getCategoryName())) {
            query += " AND LOWER(p.category.name) = LOWER(:categoryName)";
        }
        if (StringUtils.isNotBlank(criteria.getSortBy())) {
            String sortOrder = "ASC";
            if ("desc".equalsIgnoreCase(criteria.getSortOrder())) {
                sortOrder = "DESC";
            }
            query += " ORDER BY p." + criteria.getSortBy() + " " + sortOrder;
        }

        TypedQuery<ProductEntity> typedQuery = em.createQuery(query, ProductEntity.class);

        if (StringUtils.isNotBlank(criteria.getName())) {
            typedQuery.setParameter("name", "%" + criteria.getName() + "%");
        }
        if (criteria.getMinPrice() != null) {
            typedQuery.setParameter("minPrice", criteria.getMinPrice());
        }
        if (criteria.getMaxPrice() != null) {
            typedQuery.setParameter("maxPrice", criteria.getMaxPrice());
        }
        if (criteria.getDiscount() != null) {
            typedQuery.setParameter("discount", criteria.getDiscount());
        }
        if (StringUtils.isNotBlank(criteria.getBrandName())) {
            typedQuery.setParameter("brandName", criteria.getBrandName());
        }
        if (StringUtils.isNotBlank(criteria.getCategoryName())) {
            typedQuery.setParameter("categoryName", criteria.getCategoryName());
        }

        if (criteria.getPage() != null && criteria.getLimit() != null) {
            int firstResult = (int) ((criteria.getPage() - 1) * criteria.getLimit());
            typedQuery.setFirstResult(firstResult);
            typedQuery.setMaxResults(criteria.getLimit());
        }


        return typedQuery.getResultList();
    }

    public long countProducts(ProductSearchCriteria criteria) {
        String countQuery = "SELECT COUNT(p) FROM ProductEntity p WHERE 1=1 ";

        if (StringUtils.isNotBlank(criteria.getName())) {
            countQuery += " AND p.name LIKE :name";
        }
        if (criteria.getMinPrice() != null) {
            countQuery += " AND p.price >= :minPrice";
        }
        if (criteria.getMaxPrice() != null) {
            countQuery += " AND p.price <= :maxPrice";
        }
        if (criteria.getDiscount() != null) {
            countQuery += " AND p.discount >= :discount";
        }
        if (StringUtils.isNotBlank(criteria.getBrandName())) {
            countQuery += " AND LOWER(p.brand.name) = LOWER(:brandName)";
        }
        if (StringUtils.isNotBlank(criteria.getCategoryName())) {
            countQuery += " AND LOWER(p.category.name) = LOWER(:categoryName)";
        }

        TypedQuery<Long> countTypedQuery = em.createQuery(countQuery, Long.class);
        if (StringUtils.isNotBlank(criteria.getName())) {
            countTypedQuery.setParameter("name", "%" + criteria.getName() + "%");
        }
        if (criteria.getMinPrice() != null) {
            countTypedQuery.setParameter("minPrice", criteria.getMinPrice());
        }
        if (criteria.getMaxPrice() != null) {
            countTypedQuery.setParameter("maxPrice", criteria.getMaxPrice());
        }
        if (criteria.getDiscount() != null) {
            countTypedQuery.setParameter("discount", criteria.getDiscount());
        }
        if (StringUtils.isNotBlank(criteria.getBrandName())) {
            countTypedQuery.setParameter("brandName", criteria.getBrandName());
        }
        if (StringUtils.isNotBlank(criteria.getCategoryName())) {
            countTypedQuery.setParameter("categoryName", criteria.getCategoryName());
        }

        return countTypedQuery.getSingleResult();
    }



}
