package com.cantave.mealPlanGenerator.services;

import com.cantave.mealPlanGenerator.models.Recipe;
import com.cantave.mealPlanGenerator.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe addRecipe(Recipe recipe){
        return recipeRepository.save(recipe);
    }

    public List<Recipe> getAllRecipes(){
        return recipeRepository.findAll();
    }
}
