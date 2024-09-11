package com.ecommerce.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String address;

    @Column
    private String avatar;

    @Column
    private String email;

    @Column( name = "full_name")
    private String fullName;

    @Column
    private String password;

    @Column
    private String phone;

    @Column
    private boolean active;

    @ManyToOne
    @JoinColumn( name = "role_id")
    private RoleEntity role;

    @OneToMany( mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
    private List<OrderEntity> orders;

    @OneToMany( mappedBy = "userComment", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
    private List<CommentEntity> commentList;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private CartEntity cart;

    @OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
    private List<WishListEntity> wishList;


}
