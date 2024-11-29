package com.ecommerce.api.login;

import com.ecommerce.config.jwt.JwtUtil;
import com.ecommerce.entity.RoleEntity;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.model.dto.UserDTO;
import com.ecommerce.model.request.LoginRequest;
import com.ecommerce.model.request.RegisterRequest;
import com.ecommerce.model.response.LoginResponse;
import com.ecommerce.model.response.RegisterResponse;
import com.ecommerce.model.response.Response;
import com.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class LoginAPI {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @PostMapping("/login")
    public Response<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (AuthenticationException exception) {
            return new Response<Object>("Bad credentials", exception );
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwtToken = jwtUtil.generateTokenFromUsername(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        UserEntity user = userService.findUserByEmail(userDetails.getUsername());
        LoginResponse response = new LoginResponse( jwtToken,user,roles);

        return new Response<>( "Đăng nhập thành công", response);
    }

    @PostMapping("/register")
    public Response<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        userService.register(registerRequest);
        UserEntity newUser = userService.findUserByEmail(registerRequest.getEmail());
        RegisterResponse response = new RegisterResponse(newUser);

        return new Response<>("Đăng ký thành công", response);
    }
}

