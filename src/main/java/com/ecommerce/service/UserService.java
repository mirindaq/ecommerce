package com.ecommerce.service;

import com.ecommerce.model.dto.UserDTO;
import com.ecommerce.model.request.RegisterRequest;

public interface UserService {
    UserDTO addOrUpdateUser(UserDTO userDTO);

    UserDTO findUserByEmail(String email);

    void register(RegisterRequest registerRequest);
}
