package com.ecommerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
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
    @JsonIgnore
    private String password;

    @Column
    private String phone;

    @Column
    private boolean active;

    @ManyToOne
    @JoinColumn( name = "role_id")
    @JsonIgnore
    private RoleEntity role;

    @OneToMany( mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonIgnore
    private List<OrderEntity> orders;

    @OneToMany( mappedBy = "userComment", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonIgnore
    private List<CommentEntity> commentList;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonIgnore
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private CartEntity cart;

    @OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonIgnore
    private List<WishListEntity> wishList;



}
