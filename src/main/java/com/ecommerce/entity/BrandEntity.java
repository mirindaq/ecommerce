package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "brands")
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany( mappedBy = "brand", fetch = FetchType.LAZY,
            cascade = { CascadeType.MERGE, CascadeType.PERSIST})
    private List<ProductEntity> products;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY,
            cascade = { CascadeType.MERGE, CascadeType.PERSIST})
    private List<BrandCategoryEntity> brandCategoryEntities;
}
