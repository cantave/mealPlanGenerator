package com.cantave.mealPlanGenerator.controllers;

import com.cantave.mealPlanGenerator.models.MealPlan;
import com.cantave.mealPlanGenerator.services.MealPlanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MealPlanController.class)
public class MealPlanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MealPlanService mealPlanService;

    @InjectMocks
    private MealPlanController mealPlanController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllMealPlans() throws Exception {
        MealPlan mealPlan1 = new MealPlan();
        MealPlan mealPlan2 = new MealPlan();
        when(mealPlanService.getAllMealPlans(1L)).thenReturn(Arrays.asList(mealPlan1, mealPlan2));

        mockMvc.perform(get("/api/mealplans/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").exists());
    }

    @Test
    public void testGetMealPlanById() throws Exception {
        MealPlan mealPlan = new MealPlan();
        mealPlan.setId(1L);
        when(mealPlanService.getMealPlanById(1L)).thenReturn(mealPlan);

        mockMvc.perform(get("/api/mealplans/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testGenerateMealPlan() throws Exception {
        MealPlan mealPlan = new MealPlan();
        when(mealPlanService.generateMealPlan(any(MealPlan.class))).thenReturn(mealPlan);

        mockMvc.perform(post("/api/mealplans")
                        .contentType("application/json")
                        .content("{ \"userId\": 1, \"breakfast\": [\"Pancakes\"], \"lunch\": [\"Sandwich\"], \"dinner\": [\"Steak\"], \"date\": \"2023-01-01\", \"recipes\": [] }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1));
    }

    @Test
    public void testUpdateMealPlan() throws Exception {
        MealPlan updatedMealPlan = new MealPlan();
        updatedMealPlan.setId(1L);
        updatedMealPlan.setBreakfast(Arrays.asList("Pancakes"));

        when(mealPlanService.updateMealPlan(any(Long.class), any(MealPlan.class))).thenReturn(updatedMealPlan);

        mockMvc.perform(post("/api/mealplans/1")
                        .contentType("application/json")
                        .content("{ \"breakfast\": [\"Pancakes\"], \"lunch\": [\"Sandwich\"], \"dinner\": [\"Steak\"], \"date\": \"2023-01-01\", \"recipes\": [] }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.breakfast[0]").value("Pancakes"));
    }

    @Test
    public void testDeleteMealPlan() throws Exception {
        doNothing().when(mealPlanService).deleteMealPlan(1L);

        mockMvc.perform(delete("/api/mealplans/1"))
                .andExpect(status().isOk());
    }
}
