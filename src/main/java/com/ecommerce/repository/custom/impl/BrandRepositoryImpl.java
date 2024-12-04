package com.ecommerce.repository.custom.impl;

import com.ecommerce.entity.BrandEntity;
import com.ecommerce.repository.custom.BrandRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class BrandRepositoryImpl implements BrandRepositoryCustom {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<BrandEntity> getBrandByCategoryName(String categoryName) {
        TypedQuery<BrandEntity> query = em.createQuery(
                "SELECT bc.brand FROM BrandCategoryEntity bc WHERE bc.category.name = :categoryName",
                BrandEntity.class
        );
        query.setParameter("categoryName", categoryName);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return List.of();
        }
    }

}
