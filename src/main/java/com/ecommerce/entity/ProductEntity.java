package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Double price;

    @Column
    private Integer stock;

    @Column
    private Double discount;

    @Column
    private String description;

    @Column
    private String image;

    @Column
    private boolean active;

    @OneToMany( mappedBy = "productWishList" , fetch = FetchType.LAZY,
            cascade = { CascadeType.MERGE, CascadeType.PERSIST},
            orphanRemoval = true)
    private List<WishListEntity> wishlist;

    @OneToMany( mappedBy = "productComment", fetch = FetchType.LAZY,
            cascade = { CascadeType.MERGE, CascadeType.PERSIST},
            orphanRemoval = true)
    private List<CommentEntity> commentList;

    @OneToMany( mappedBy = "productOrder", fetch = FetchType.LAZY,
            cascade = { CascadeType.MERGE, CascadeType.PERSIST},
             orphanRemoval = true)
    private List<OrderDetailEntity> orderDetailEntities;


    @OneToMany( mappedBy = "productCart", fetch = FetchType.LAZY,
            cascade = { CascadeType.MERGE, CascadeType.PERSIST},
            orphanRemoval = true)
    private List<CartDetailEntity> cartDetailEntities;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "productAttribute", fetch = FetchType.LAZY,
            cascade = { CascadeType.MERGE, CascadeType.PERSIST},
            orphanRemoval = true)
    private List<AttributeDetailEntity> attributeList;



}
