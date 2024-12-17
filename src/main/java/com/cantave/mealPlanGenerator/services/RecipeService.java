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

    public Recipe getRecipeById(Long id){
        return recipeRepository.findById(id).orElse(null);
    }

    public List<Recipe> getAllRecipes(){
        return recipeRepository.findAll();
    }

    public Recipe updateRecipe(Long id, Recipe recipeDetails){
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        if(recipe != null) {
            recipe.setName(recipeDetails.getName());
            recipe.setDescription((recipe.getDescription()));
            recipe.setCategory(recipeDetails.getCategory());
            recipe.setArea(recipeDetails.getArea());
            recipe.setMealThumb(recipeDetails.getMealThumb());
            recipe.setYoutube(recipeDetails.getYoutube());
            recipe.setIngredients(recipeDetails.getIngredients());
            recipe.setMeasures(recipeDetails.getMeasures());
            recipe.setInstructions(recipe.getInstructions());
            return recipeRepository.save(recipe);
        }
        return null;
    }

    public void deleteRecipe(Long id){
        recipeRepository.deleteById(id);
    }
}
