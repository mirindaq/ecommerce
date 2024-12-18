package com.ecommerce.api.admin;

import com.ecommerce.model.dto.UserDTO;
import com.ecommerce.model.response.Response;
import com.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserAPI {

    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Response<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
        return new Response<>("ok",userDTO);
    }

    @GetMapping("/employees")
    @PreAuthorize("hasRole('ADMIN')")
    public Response<Map<String, List<UserDTO>> > getAllUsersByName() {
        List<UserDTO> userDTOS = userService.findAllUsersByNames(List.of("STAFF") );
        Map<String, List<UserDTO>> map = new HashMap<>();
        map.put("users",userDTOS);
        return new Response<>("ok",map);
    }



}
