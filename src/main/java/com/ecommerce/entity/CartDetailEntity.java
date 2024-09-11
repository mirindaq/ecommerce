package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cart_detail")
public class CartDetailEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;

    private Long quantity;

    @ManyToOne
    @JoinColumn( name = "product_id" )
    private ProductEntity productCart;

    @ManyToOne
    @JoinColumn( name = "cart_id")
    private CartEntity cartUser;

}
