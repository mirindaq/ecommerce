package com.ecommerce.api.client;

import com.ecommerce.model.dto.UserDTO;
import com.ecommerce.model.response.Response;
import com.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileAPI {

    private final UserService userService;

    @PutMapping
    public Response<UserDTO> updateUserByClient(@RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
        return new Response<>("ok",userDTO);
    }
}
