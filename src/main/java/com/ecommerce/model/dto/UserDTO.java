package com.ecommerce.model.dto;

import com.ecommerce.entity.CartEntity;
import com.ecommerce.entity.OrderEntity;
import com.ecommerce.entity.RoleEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends AbstractDTO {
    private Long id;
    private String address;
    private String avatar;
    private String email;
    private String fullName;
    private String password;
    private String phone;
    private boolean active;
    private Long roleId;
    private List<Long> orderIds;
    private List<Long> commentIds;
    private Long cartId;
    private List<Long> wishListIds;
}