package com.cantave.mealPlanGenerator.controllers;

import com.cantave.mealPlanGenerator.models.User;
import com.cantave.mealPlanGenerator.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/{id}")
    public User getUserProfile(@PathVariable Long id){
        return userProfileService.getUserProfile(id);
    }

    @PutMapping("/{id}")
    public User updateUserProfile(@PathVariable Long id, @RequestBody User userDetails){
        return userProfileService.updateUserProfile(id, userDetails);
    }
}
