package com.cantave.mealPlanGenerator.services;

import com.cantave.mealPlanGenerator.models.MealPlan;
import com.cantave.mealPlanGenerator.repositories.MealPlanRepository;
import com.cantave.mealPlanGenerator.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealPlanService {
    @Autowired
    private MealPlanRepository mealPlanRepository;

    public List<MealPlan> getAllMealPlans(Long userId) {
        return mealPlanRepository.findByUserId(userId);
    }

    public MealPlan getMealPlanById(Long id) {
        return mealPlanRepository.findById(id).orElse(null);
    }

    public MealPlan generateMealPlan(MealPlan mealPlan) {
        return mealPlanRepository.save(mealPlan);
    }

    public MealPlan updateMealPlan(Long id, MealPlan mealPlanDetails) {
        MealPlan mealPlan = mealPlanRepository.findById(id).orElse(null);
        if (mealPlan != null) {
            mealPlan.setBreakfast(mealPlanDetails.getBreakfast());
            mealPlan.setLunch(mealPlanDetails.getLunch());
            mealPlan.setDinner(mealPlanDetails.getDinner());
            mealPlan.setDate(mealPlanDetails.getDate());
            mealPlan.setRecipes(mealPlanDetails.getRecipes());
            return mealPlanRepository.save(mealPlan);
        }
        return null;
    }

    public void deleteMealPlan(Long id) {
        mealPlanRepository.deleteById(id);
    }

    public MealPlan removeRecipeFromMealPlan(Long mealPlanId, Long recipeId) {
        MealPlan mealPlan = mealPlanRepository.findById(mealPlanId).orElse(null);
        if (mealPlan != null) {
            mealPlan.setRecipes(
                    mealPlan.getRecipes().stream()
                            .filter(recipe -> !recipe.getId().equals(recipeId))
                            .toList()
            );
            return mealPlanRepository.save(mealPlan);
        }
        return null;
    }
}
