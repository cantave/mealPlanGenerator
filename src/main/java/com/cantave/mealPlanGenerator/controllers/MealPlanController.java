package com.cantave.mealPlanGenerator.controllers;

import com.cantave.mealPlanGenerator.models.MealPlan;
import com.cantave.mealPlanGenerator.services.MealPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mealplans")
public class MealPlanController {
    @Autowired
    private MealPlanService mealPlanService;

    @GetMapping("/{userId}")
    public List<MealPlan> getAllMealPlans(@PathVariable Long userId) {
        return mealPlanService.getAllMealPlans(userId);
    }

    @GetMapping("/{id}")
    public MealPlan getMealPlanById(@PathVariable Long id){
        return mealPlanService.getMealPlanById(id);
    }

    @PostMapping
    public MealPlan generateMealPlan(@RequestBody MealPlan mealPlan){
        return mealPlanService.generateMealPlan(mealPlan);
    }

    @PostMapping("/{id}")
    public MealPlan updateMealPlan(@PathVariable Long id, @RequestBody MealPlan mealPlanDetails){
        return mealPlanService.updateMealPlan(id, mealPlanDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteMealPlan(@PathVariable Long id){
        mealPlanService.deleteMealPlan(id);
    }


}
