package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "wish_lists")
public class WishListEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn( name = "product_id" )
    private ProductEntity productWishList;

    @ManyToOne
    @JoinColumn( name = "user_id")
    private UserEntity user;

}
