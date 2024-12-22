package com.ecommerce.service;

import com.ecommerce.entity.UserEntity;
import com.ecommerce.model.dto.UserDTO;
import com.ecommerce.model.request.RegisterRequest;

import java.util.List;

public interface UserService {
    UserDTO addUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO);

    UserEntity findUserByEmail(String email);

    List<UserDTO> findAllUsersByNames( List<String> names);

    void register(RegisterRequest registerRequest);
}
