package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "attributes")
public class AttributeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany( mappedBy = "attribute", fetch = FetchType.LAZY,
            cascade = { CascadeType.MERGE, CascadeType.PERSIST},
            orphanRemoval = true)
    private List<AttributeDetailEntity> detailEntityList;
}
