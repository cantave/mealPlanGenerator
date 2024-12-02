package com.cantave.mealPlanGenerator.services;

import com.cantave.mealPlanGenerator.dto.RegisterRequest;
import com.cantave.mealPlanGenerator.models.User;
import com.cantave.mealPlanGenerator.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Doe");
        registerRequest.setUsername("johndoe");
        registerRequest.setPassword("password");

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("johndoe");
        user.setPassword("encodedPassword");

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.registerUser(registerRequest);

        assertEquals("John", savedUser.getFirstName());
        assertEquals("Doe", savedUser.getLastName());
        assertEquals("johndoe", savedUser.getUsername());
        assertEquals("encodedPassword", savedUser.getPassword());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testFindByUsername() {
        User user = new User();
        user.setUsername("johndoe");

        when(userRepository.findByUsername("johndoe")).thenReturn(user);

        User foundUser = userService.findByUsername("johndoe");

        assertEquals("johndoe", foundUser.getUsername());
        verify(userRepository, times(1)).findByUsername("johndoe");
    }
}
