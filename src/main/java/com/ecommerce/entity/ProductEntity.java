package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "products")
public class  ProductEntity extends BaseEntity {

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

    @Column( columnDefinition = "LONGTEXT")
    private String description;


    @Column
    private boolean active;

    @Column
    private Double rating;

    @OneToMany( mappedBy = "productWishList" , fetch = FetchType.LAZY,
            cascade = { CascadeType.MERGE, CascadeType.PERSIST})
    private List<WishListEntity> wishlist;

    @OneToMany( mappedBy = "productComment", fetch = FetchType.LAZY,
            cascade = { CascadeType.MERGE, CascadeType.PERSIST})
    private List<CommentEntity> commentList;

    @OneToMany( mappedBy = "productOrder", fetch = FetchType.LAZY,
            cascade = { CascadeType.MERGE, CascadeType.PERSIST})
    private List<OrderDetailEntity> orderDetailEntities;


    @OneToMany( mappedBy = "productCart", fetch = FetchType.LAZY,
            cascade = { CascadeType.MERGE, CascadeType.PERSIST})
    private List<CartDetailEntity> cartDetailEntities;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,
            cascade = { CascadeType.MERGE, CascadeType.PERSIST})
    private List<AttributeDetailEntity> attributeList;


    @OneToMany(mappedBy = "product", cascade = { CascadeType.MERGE, CascadeType.PERSIST})
    private List<ProductImageEntity> productImageEntityList = new ArrayList<>();
}
