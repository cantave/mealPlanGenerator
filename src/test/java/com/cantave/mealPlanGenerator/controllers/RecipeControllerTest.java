package com.cantave.mealPlanGenerator.controllers;

import com.cantave.mealPlanGenerator.models.Recipe;
import com.cantave.mealPlanGenerator.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    @Test
    public void testGetAllRecipes() throws Exception {
        Recipe recipe1 = new Recipe();
        recipe1.setName("Recipe 1");
        Recipe recipe2 = new Recipe();
        recipe2.setName("Recipe 2");

        when(recipeService.getAllRecipes()).thenReturn(Arrays.asList(recipe1, recipe2));

        mockMvc.perform(get("/api/recipes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Recipe 1"))
                .andExpect(jsonPath("$[1].name").value("Recipe 2"));
    }

    @Test
    public void testGetRecipeById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setName("Test Recipe");

        when(recipeService.getRecipeById(1L)).thenReturn(recipe);

        mockMvc.perform(get("/api/recipes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Recipe"));
    }

    @Test
    public void testAddRecipe() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setName("New Recipe");

        when(recipeService.addRecipe(any(Recipe.class))).thenReturn(recipe);

        mockMvc.perform(post("/api/recipes")
                        .contentType("application/json")
                        .content("{ \"name\": \"New Recipe\", \"ingredients\": [\"ingredient1\", \"ingredient2\"], \"instructions\": \"Mix ingredients\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Recipe"));
    }

    @Test
    public void testUpdateRecipe() throws Exception {
        Recipe updatedRecipe = new Recipe();
        updatedRecipe.setId(1L);
        updatedRecipe.setName("Updated Recipe");

        when(recipeService.updateRecipe(any(Long.class), any(Recipe.class))).thenReturn(updatedRecipe);

        mockMvc.perform(put("/api/recipes/1")
                        .contentType("application/json")
                        .content("{ \"name\": \"Updated Recipe\", \"ingredients\": [\"ingredient1\", \"ingredient2\"], \"instructions\": \"Mix ingredients\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Recipe"));
    }

    @Test
    public void testDeleteRecipe() throws Exception {
        doNothing().when(recipeService).deleteRecipe(1L);

        mockMvc.perform(delete("/api/recipes/1"))
                .andExpect(status().isOk());
    }
}
