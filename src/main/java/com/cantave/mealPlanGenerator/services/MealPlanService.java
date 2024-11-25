package com.cantave.mealPlanGenerator.services;

import com.cantave.mealPlanGenerator.models.MealPlan;
import com.cantave.mealPlanGenerator.repositories.MealPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MealPlanService {
    @Autowired
    private MealPlanRepository mealPlanRepository;

    public MealPlan generateMealPlan(MealPlan mealPlan){
        return mealPlanRepository.save(mealPlan);
    }

    public Optional<MealPlan> getMealPlansByUserId (Long userId){
        return mealPlanRepository.findById(userId);
    }
}
