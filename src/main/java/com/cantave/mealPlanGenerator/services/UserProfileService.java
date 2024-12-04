package com.cantave.mealPlanGenerator.services;

import com.cantave.mealPlanGenerator.models.User;
import com.cantave.mealPlanGenerator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    private UserRepository userRepository;

    public User getUserProfile(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateUserProfile(Long userId, User userDetails) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setEmail(userDetails.getEmail());
            user.setMealPreferences(userDetails.getMealPreferences());
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUserProfile(Long userId) {
        userRepository.deleteById(userId);
    }
}
