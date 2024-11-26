package com.cantave.mealPlanGenerator.controllers;

import com.cantave.mealPlanGenerator.dto.RegisterRequest;
import com.cantave.mealPlanGenerator.models.User;
import com.cantave.mealPlanGenerator.services.UserService;
import com.cantave.mealPlanGenerator.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password));
        } catch (Exception ex) {
            throw new Exception("Invalid username or password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.username);
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return jwt;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest){
        User user = userService.registerUser(registerRequest);
        return "User registered successfully: " + user.getUsername();
    }
}

class LoginRequest {
    public String username;
    public String password;
}
