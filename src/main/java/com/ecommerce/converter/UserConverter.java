package com.ecommerce.converter;

import com.ecommerce.entity.UserEntity;
import com.ecommerce.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final ModelMapper modelMapper;

    public UserEntity fromDTOToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }

    public UserDTO fromEntityToDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }

}
