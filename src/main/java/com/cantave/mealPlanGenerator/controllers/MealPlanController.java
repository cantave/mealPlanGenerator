package com.cantave.mealPlanGenerator.controllers;

import com.cantave.mealPlanGenerator.models.MealPlan;
import com.cantave.mealPlanGenerator.services.MealPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/mealplans")
public class MealPlanController {
    @Autowired
    private MealPlanService mealPlanService;

    @PostMapping
    public MealPlan generateMealPlan(@RequestBody MealPlan mealPlan){
        return mealPlanService.generateMealPlan(mealPlan);
    }

    @GetMapping("/{userId}")
    public Optional<MealPlan> getMealPlansByUserId(@PathVariable Long userId) {
        return mealPlanService.getMealPlansByUserId(userId);
    }
}
