package com.ecommerce.service;

import com.ecommerce.model.dto.UserDTO;

public interface UserService {
    UserDTO addOrUpdateUser(UserDTO userDTO);
}
