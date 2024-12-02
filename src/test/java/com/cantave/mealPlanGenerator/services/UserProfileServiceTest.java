package com.cantave.mealPlanGenerator.services;

import com.cantave.mealPlanGenerator.models.User;
import com.cantave.mealPlanGenerator.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserProfileServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserProfileService userProfileService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserProfile() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userProfileService.getUserProfile(1L);

        assertEquals("John", foundUser.getFirstName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateUserProfile() {
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setFirstName("John");

        User updatedUser = new User();
        updatedUser.setFirstName("Jane");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = userProfileService.updateUserProfile(1L, updatedUser);

        assertEquals("Jane", result.getFirstName());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(existingUser);
    }
}
