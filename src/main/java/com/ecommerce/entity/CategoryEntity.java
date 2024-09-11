package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany( mappedBy = "category" , fetch = FetchType.LAZY,
            cascade = { CascadeType.MERGE, CascadeType.PERSIST},
            orphanRemoval = true)
    private List<ProductEntity> products;
}
