package com.cantave.mealPlanGenerator.services;

import com.cantave.mealPlanGenerator.models.Recipe;
import com.cantave.mealPlanGenerator.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddRecipe() {
        Recipe recipe = new Recipe();
        recipe.setName("Test Recipe");

        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);

        Recipe savedRecipe = recipeService.addRecipe(recipe);
        assertEquals("Test Recipe", savedRecipe.getName());
        verify(recipeRepository, times(1)).save(recipe);
    }

    @Test
    public void testGetRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId(3L);
        recipe.setName("Test Recipe");

        when(recipeRepository.findById(3L)).thenReturn(Optional.of(recipe));

        Recipe foundRecipe = recipeService.getRecipeById(3L);
        assertEquals("Test Recipe", foundRecipe.getName());
    }

    @Test
    public void testGetRecipeById_NotFound() {
        when(recipeRepository.findById(999L)).thenReturn(Optional.empty());

        Recipe foundRecipe = recipeService.getRecipeById(999L);

        assertNull(foundRecipe);
        verify(recipeRepository, times(1)).findById(999L);
    }

    @Test
    public void testUpdateRecipe() {
        Recipe existingRecipe = new Recipe();
        existingRecipe.setId(3L);
        existingRecipe.setName("Old Recipe");

        Recipe updatedRecipe = new Recipe();
        updatedRecipe.setName("Updated Recipe");

        when(recipeRepository.findById(3L)).thenReturn(Optional.of(existingRecipe));
        when(recipeRepository.save(any(Recipe.class))).thenReturn(updatedRecipe);

        Recipe result = recipeService.updateRecipe(3L, updatedRecipe);

        assertEquals("Updated Recipe", result.getName());
        verify(recipeRepository, times(1)).findById(3L);
        verify(recipeRepository, times(1)).save(existingRecipe);
    }


    @Test
    public void testDeleteRecipe() {
        doNothing().when(recipeRepository).deleteById(4L);

        recipeService.deleteRecipe(4L);

        verify(recipeRepository, times(1)).deleteById(4L);
    }


}
