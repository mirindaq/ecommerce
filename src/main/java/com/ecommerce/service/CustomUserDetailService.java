package com.ecommerce.service;

import com.ecommerce.config.jwt.UserDetailsCustom;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the database (e.g., by email)
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Convert User entity to UserDetailsCustom
        return new UserDetailsCustom(user.getEmail(), user.getPassword(), user.getRole().getName());
    }
}

