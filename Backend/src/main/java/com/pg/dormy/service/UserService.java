package com.pg.dormy.service;

import com.pg.dormy.entity.User;
import com.pg.dormy.model.AuthResponse;
import com.pg.dormy.model.LoginUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService  {

    User loadUserByUserPhone(String phone);
    AuthResponse registerUser(User user);
    AuthResponse loginUser(String  phoneNumber);
}
