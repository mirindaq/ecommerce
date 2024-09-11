package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class OrderEntity extends BaseEntity{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false , name = "receiver_address")
    private String receiverAddress;

    @Column( nullable = false, name = "receiver_name")
    private String receiverName;

    @Column( nullable = false, name = "reiceiver_phone")
    private String reiceiverPhone;

    @Column( name = "order_date")
    private LocalDate orderDate;

    @Column
    private String status;

    @Column( name = "total_price")
    private Double totalPrice;

    @ManyToOne( fetch = FetchType.EAGER )
    private UserEntity user;

    @OneToMany(mappedBy = "order",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            orphanRemoval = true)
    private List<OrderDetailEntity> orderDetailEntities;

}
