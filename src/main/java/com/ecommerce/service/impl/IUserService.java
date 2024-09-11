package com.ecommerce.service.impl;

import com.ecommerce.converter.UserConverter;
import com.ecommerce.entity.RoleEntity;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.model.dto.UserDTO;
import com.ecommerce.repository.RoleRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class IUserService implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserConverter userConverter;

    @Override
    @Transactional
    public UserDTO addOrUpdateUser(UserDTO userDTO) {
        UserEntity userEntity = userConverter.fromDTOToEntity(userDTO);
        RoleEntity role = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        userEntity.setRole(role);
        userEntity.setActive(true);
        return userConverter.fromEntityToDTO(userRepository.save(userEntity));
    }



}
