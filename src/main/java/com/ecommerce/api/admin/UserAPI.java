package com.ecommerce.api.admin;

import com.ecommerce.model.dto.UserDTO;
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
    public ResponseEntity<UserDTO> addOrUpdateUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.addOrUpdateUser(userDTO));
    }

}
