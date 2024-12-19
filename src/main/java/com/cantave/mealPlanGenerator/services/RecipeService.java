package com.cantave.mealPlanGenerator.services;

import com.cantave.mealPlanGenerator.models.MealPlan;
import com.cantave.mealPlanGenerator.models.Recipe;
import com.cantave.mealPlanGenerator.repositories.MealPlanRepository;
import com.cantave.mealPlanGenerator.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private MealPlanRepository mealPlanRepository;

    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe updateRecipe(Long id, Recipe recipeDetails) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        if (recipe != null) {
            recipe.setName(recipeDetails.getName());
            recipe.setDescription(recipeDetails.getDescription());
            recipe.setCategory(recipeDetails.getCategory());
            recipe.setArea(recipeDetails.getArea());
            recipe.setMealThumb(recipeDetails.getMealThumb());
            recipe.setYoutube(recipeDetails.getYoutube());
            recipe.setIngredients(recipeDetails.getIngredients());
            recipe.setMeasures(recipeDetails.getMeasures());
            recipe.setInstructions(recipeDetails.getInstructions());
            return recipeRepository.save(recipe);
        }
        return null;
    }

    public void deleteRecipe(Long id){
        List<MealPlan> mealPlans = mealPlanRepository.findAll();

        for(MealPlan mealPlan : mealPlans){
            mealPlan.getBreakfast().removeIf(recipe -> recipe.getId().equals(id));
            mealPlan.getLunch().removeIf(recipe -> recipe.getId().equals(id));
            mealPlan.getDinner().removeIf(recipe -> recipe.getId().equals(id));
            mealPlan.getRecipes().removeIf(recipe -> recipe.getId().equals(id));
            mealPlanRepository.save(mealPlan);
        }

        recipeRepository.deleteById(id);
    }

    public void addToMealPlan(Recipe recipe, String mealTime, Long mealPlanId) {
        if (recipe.getInstructions() == null || recipe.getInstructions().isEmpty()) {
            throw new IllegalArgumentException("Instructions cannot be null or empty");
        }

        Recipe savedRecipe = recipeRepository.save(recipe);

        MealPlan mealPlan = mealPlanRepository.findById(mealPlanId)
                .orElseThrow(() -> new RuntimeException("Meal plan not found"));

        switch (mealTime.toLowerCase()) {
            case "breakfast":
                mealPlan.getBreakfast().add(savedRecipe);
                break;
            case "lunch":
                mealPlan.getLunch().add(savedRecipe);
                break;
            case "dinner":
                mealPlan.getDinner().add(savedRecipe);
                break;
            default:
                throw new IllegalArgumentException("Invalid meal time: " + mealTime);
        }
        mealPlan.getRecipes().add(savedRecipe);
        mealPlanRepository.save(mealPlan);
    }
}
