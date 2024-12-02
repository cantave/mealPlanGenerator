package com.cantave.mealPlanGenerator.services;

import com.cantave.mealPlanGenerator.models.User;
import com.cantave.mealPlanGenerator.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername_UserExists() {
        User user = new User();
        user.setUsername("johndoe");
        user.setPassword("password");

        when(userRepository.findByUsername("johndoe")).thenReturn(user);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("johndoe");

        assertEquals("johndoe", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertEquals(Collections.emptyList(), new ArrayList<>(userDetails.getAuthorities()));
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByUsername("unknown")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () ->
                customUserDetailsService.loadUserByUsername("unknown"));
    }
}
