package com.ecommerce.service.impl;

import com.ecommerce.converter.UserConverter;
import com.ecommerce.entity.RoleEntity;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.exception.custom.EmailAlreadyExistsException;
import com.ecommerce.model.dto.UserDTO;
import com.ecommerce.model.request.RegisterRequest;
import com.ecommerce.repository.RoleRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class IUserService implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDTO addUser(UserDTO userDTO) {
        UserEntity userEntity = userConverter.fromDTOToEntity(userDTO);
        RoleEntity role = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        userEntity.setRole(role);
        userEntity.setActive(true);
        return userConverter.fromEntityToDTO(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public UserDTO updateUser(UserDTO userDTO) {
        UserEntity userEntity = findUserByEmail(userDTO.getEmail());
        if (userEntity == null) {
            throw new RuntimeException("User not found");
        }

        userEntity.setFullName(userDTO.getFullName());
        userEntity.setAddress(userDTO.getAddress());
        userEntity.setPhone(userDTO.getPhone());


        return userConverter.fromEntityToDTO(userRepository.save(userEntity));
    }


    @Override
    public UserEntity findUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElse(null);
        if ( userEntity == null) return null;
        return userEntity;
    }

    @Override
    public List<UserDTO> findAllUsersByNames( List<String> names) {
        List<UserEntity> userEntities = userRepository.findAllByRoleName(names);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            userDTOs.add(userConverter.fromEntityToDTO(userEntity));
        }
        return userDTOs;
    }

    @Override
    public void register(RegisterRequest registerRequest) {
        if (findUserByEmail(registerRequest.getEmail()) != null) {
            throw new EmailAlreadyExistsException("Email đã tồn tại");
        }
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        RoleEntity role = roleRepository.findByName("USER");
        UserEntity newUser = new UserEntity();
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(encodedPassword);
        newUser.setRole(role);
        newUser.setActive(true);
        userRepository.save(newUser);
    }


}
