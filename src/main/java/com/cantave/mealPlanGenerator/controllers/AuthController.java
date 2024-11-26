package com.cantave.mealPlanGenerator.controllers;

import com.cantave.mealPlanGenerator.dto.AuthenticationResponse;
import com.cantave.mealPlanGenerator.dto.LoginRequest;
import com.cantave.mealPlanGenerator.dto.RegisterRequest;
import com.cantave.mealPlanGenerator.models.User;
import com.cantave.mealPlanGenerator.services.UserService;
import com.cantave.mealPlanGenerator.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(), loginRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            System.out.println("Authentication failed for user: " + loginRequest.getUsername());
            throw new Exception("Invalid username or password", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(loginRequest.getUsername());

        final User user = userService.findByUsername(loginRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername(), user.getId());
        System.out.println("Generated JWT for user: " + loginRequest.getUsername());
        System.out.println("JWT: " + jwt); // Log JWT to verify payload
        System.out.println("User ID: " + user.getId()); // Log user ID to ensure it's fetched correctly
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest){
        User user = userService.registerUser(registerRequest);
        return "User registered successfully: " + user.getUsername();
    }
}
