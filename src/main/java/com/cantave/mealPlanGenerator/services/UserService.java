package com.cantave.mealPlanGenerator.services;

import com.cantave.mealPlanGenerator.dto.RegisterRequest;
import com.cantave.mealPlanGenerator.models.User;
import com.cantave.mealPlanGenerator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(RegisterRequest registerRequest){
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
