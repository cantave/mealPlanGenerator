package com.cantave.mealPlanGenerator.controllers;

import com.cantave.mealPlanGenerator.models.Recipe;
import com.cantave.mealPlanGenerator.repositories.RecipeRepository;
import com.cantave.mealPlanGenerator.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe){
        return recipeService.addRecipe(recipe);
    }

    @GetMapping
    public List<Recipe> getAllRecipes(){
        return recipeService.getAllRecipes();
    }
}
