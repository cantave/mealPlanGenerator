package com.cantave.mealPlanGenerator.controllers;

import com.cantave.mealPlanGenerator.models.User;
import com.cantave.mealPlanGenerator.services.UserProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserProfileController.class)
public class UserProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserProfileService userProfileService;

    @InjectMocks
    private UserProfileController userProfileController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserProfile() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");

        when(userProfileService.getUserProfile(1L)).thenReturn(user);

        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    public void testUpdateUserProfile() throws Exception {
        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setFirstName("Jane");

        when(userProfileService.updateUserProfile(any(Long.class), any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/api/user/1")
                        .contentType("application/json")
                        .content("{ \"firstName\": \"Jane\", \"lastName\": \"Doe\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jane"));
    }

    @Test
    public void testDeleteUserProfile() throws Exception {
        Long userId = 1L;

        doNothing().when(userProfileService).deleteUserProfile(userId);

        mockMvc.perform(delete("/api/user/{id}", userId))
                .andExpect(status().isOk());

        verify(userProfileService, times(1)).deleteUserProfile(userId);
    }
}
