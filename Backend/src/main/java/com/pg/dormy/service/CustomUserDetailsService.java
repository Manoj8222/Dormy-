package com.pg.dormy.service;

import com.pg.dormy.entity.User;
import com.pg.dormy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        // Find user by phone number
        User user = userRepository.findUserByPhoneNumber(phoneNumber);
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with phone number: " + phoneNumber));

        // Convert UserEntity to Spring Security UserDetails
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getPhoneNumber())
                .build();
    }
}
