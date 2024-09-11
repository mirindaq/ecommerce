package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "comments")
public class CommentEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Column
    private String rate;

    @Column
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn( name = "product_id")
    private ProductEntity productComment;

    @ManyToOne
    @JoinColumn( name = "user_id")
    private UserEntity userComment;




}
