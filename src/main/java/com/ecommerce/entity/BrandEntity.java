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

    @OneToMany( mappedBy = "brand", fetch = FetchType.LAZY,
            cascade = { CascadeType.MERGE, CascadeType.PERSIST},
            orphanRemoval = true)
    private List<ProductEntity> products;
}
