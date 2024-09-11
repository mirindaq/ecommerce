package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "order_detail")
public class OrderDetailEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;

    private Long quantity;

    @ManyToOne
    @JoinColumn( name = "product_id" )
    private ProductEntity productOrder;

    @ManyToOne
    @JoinColumn( name = "order_id")
    private OrderEntity order;
}
