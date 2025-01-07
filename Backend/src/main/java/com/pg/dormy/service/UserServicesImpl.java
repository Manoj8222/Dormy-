package com.pg.dormy.service;

import com.pg.dormy.Exception.UserAlreadyExistsException;
import com.pg.dormy.entity.User;
import com.pg.dormy.model.AuthResponse;
import com.pg.dormy.repository.UserRepository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserService{
    @Autowired
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserServicesImpl(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public User loadUserByUserPhone(String phone)  throws UsernameNotFoundException {
        return userRepository.findUserByPhoneNumber(phone);
    }

    @Override
    public AuthResponse registerUser(User user)  {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        // Validate input
        validateUserInput(user);

        // Check for existing user
        if (userRepository.findUserByPhoneNumber(user.getPhoneNumber()) != null) {
            throw new UserAlreadyExistsException("User with this phone number already exists");
        }

        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setGender(user.getGender());
        newUser.setAvatar(user.getAvatar());
        newUser.setUserType(user.getUserType());
        String s = String.valueOf(user.getUserType());
        newUser.setPreference(s.equals("tenant") ? user.getPreference() : null);


        User savedUser = userRepository.save(newUser);
        String token = jwtService.generateToken(savedUser);

        return new AuthResponse(token);
    }


    @Override
    public AuthResponse loginUser(String phoneNumber) {
        User user = userRepository.findUserByPhoneNumber(phoneNumber);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with phone number: " + phoneNumber);
        }

        String token = jwtService.generateToken(user);
        System.out.println(user);

        return new AuthResponse(token);
    }
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Implement phone number validation logic
        return phoneNumber != null && phoneNumber.matches("\\d{10}"); // Simple example for 10-digit number
    }

    private void validateUserInput(User user) {
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        if (user.getLastName() == null || user.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        if (user.getPhoneNumber() == null || !isValidPhoneNumber(user.getPhoneNumber())) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        if (user.getUserType() == null) {
            throw new IllegalArgumentException("User type cannot be null");
        }
        // Add more validations as needed
    }

}
