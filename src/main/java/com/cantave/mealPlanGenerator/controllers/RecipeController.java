package com.cantave.mealPlanGenerator.controllers;

import com.cantave.mealPlanGenerator.models.Recipe;
import com.cantave.mealPlanGenerator.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@PathVariable Long id, @RequestBody Recipe recipeDetails) {
        return recipeService.updateRecipe(id, recipeDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }

    @PostMapping("/add-to-meal-plan")
    public void addToMealPlan(@RequestBody Map<String, Object> requestBody) {
        String mealTime = (String) requestBody.get("mealTime");
        Long mealPlanId = Long.parseLong(requestBody.get("mealPlanId").toString());
        Recipe recipe = new Recipe(
                null,
                (String) requestBody.get("name"),
                (String) requestBody.get("description"),
                (String) requestBody.get("category"),
                (String) requestBody.get("area"),
                (String) requestBody.get("mealThumb"),
                (String) requestBody.get("youtube"),
                (List<String>) requestBody.get("ingredients"),
                (List<String>) requestBody.get("measures"),
                (String) requestBody.get("instructions")
        );
        if (recipe.getInstructions() == null || recipe.getInstructions().isEmpty()) {
            throw new IllegalArgumentException("Instructions cannot be null or empty");
        }

        recipeService.addToMealPlan(recipe, mealTime, mealPlanId);
    }
}
