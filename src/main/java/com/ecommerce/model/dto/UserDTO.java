package com.ecommerce.model.dto;

import com.ecommerce.entity.CartEntity;
import com.ecommerce.entity.OrderEntity;
import com.ecommerce.entity.RoleEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
public class UserDTO extends AbstractDTO {
    private Long id;
    private String address;
    private String avatar;
    private String email;
    private String fullName;
    private String password;
    private String phone;
    private Long roleId;
}
