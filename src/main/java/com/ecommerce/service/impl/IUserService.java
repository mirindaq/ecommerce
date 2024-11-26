package com.ecommerce.service.impl;

import com.ecommerce.converter.UserConverter;
import com.ecommerce.entity.RoleEntity;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.model.dto.UserDTO;
import com.ecommerce.model.request.RegisterRequest;
import com.ecommerce.repository.RoleRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class IUserService implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

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

    @Override
    public UserDTO findUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElse(null);
        if ( userEntity == null) return null;
        return userConverter.fromEntityToDTO(userEntity);
    }

    @Override
    public void register(RegisterRequest registerRequest) {
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        RoleEntity role = roleRepository.findByName("USER");
        UserEntity newUser = new UserEntity();
        newUser.setEmail(registerRequest.getUsername());
        newUser.setPassword(encodedPassword);
        newUser.setRole(role);
        newUser.setActive(true);
        userRepository.save(newUser);
    }


}
