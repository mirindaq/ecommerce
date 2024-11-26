package com.ecommerce.api.admin;

import com.ecommerce.model.dto.UserDTO;
import com.ecommerce.model.response.Response;
import com.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserAPI {

    private final UserService userService;

    @PostMapping
    public Response<UserDTO> addOrUpdateUser(@RequestBody UserDTO userDTO) {
        userService.addOrUpdateUser(userDTO);
        return new Response<>("ok",userDTO);
    }

}
