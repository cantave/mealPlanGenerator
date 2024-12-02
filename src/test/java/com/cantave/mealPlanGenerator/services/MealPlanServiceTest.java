package com.cantave.mealPlanGenerator.services;

import com.cantave.mealPlanGenerator.models.MealPlan;
import com.cantave.mealPlanGenerator.repositories.MealPlanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MealPlanServiceTest {

    @Mock
    private MealPlanRepository mealPlanRepository;

    @InjectMocks
    private MealPlanService mealPlanService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllMealPlans() {
        MealPlan mealPlan1 = new MealPlan();
        MealPlan mealPlan2 = new MealPlan();
        when(mealPlanRepository.findByUserId(1L)).thenReturn(Arrays.asList(mealPlan1, mealPlan2));

        List<MealPlan> mealPlans = mealPlanService.getAllMealPlans(1L);

        assertEquals(2, mealPlans.size());
        verify(mealPlanRepository, times(1)).findByUserId(1L);
    }

    @Test
    public void testGetMealPlanById() {
        MealPlan mealPlan = new MealPlan();
        mealPlan.setId(1L);
        when(mealPlanRepository.findById(1L)).thenReturn(Optional.of(mealPlan));

        MealPlan foundMealPlan = mealPlanService.getMealPlanById(1L);

        assertEquals(1L, foundMealPlan.getId());
        verify(mealPlanRepository, times(1)).findById(1L);
    }

    @Test
    public void testGenerateMealPlan() {
        MealPlan mealPlan = new MealPlan();
        when(mealPlanRepository.save(any(MealPlan.class))).thenReturn(mealPlan);

        MealPlan savedMealPlan = mealPlanService.generateMealPlan(mealPlan);

        assertEquals(mealPlan, savedMealPlan);
        verify(mealPlanRepository, times(1)).save(mealPlan);
    }

    @Test
    public void testUpdateMealPlan() {
        MealPlan existingMealPlan = new MealPlan();
        existingMealPlan.setId(1L);
        MealPlan updatedMealPlan = new MealPlan();
        updatedMealPlan.setBreakfast(Arrays.asList("Pancakes"));

        when(mealPlanRepository.findById(1L)).thenReturn(Optional.of(existingMealPlan));
        when(mealPlanRepository.save(any(MealPlan.class))).thenReturn(updatedMealPlan);

        MealPlan result = mealPlanService.updateMealPlan(1L, updatedMealPlan);

        assertEquals(updatedMealPlan.getBreakfast(), result.getBreakfast());
        verify(mealPlanRepository, times(1)).findById(1L);
        verify(mealPlanRepository, times(1)).save(existingMealPlan);
    }

    @Test
    public void testDeleteMealPlan() {
        doNothing().when(mealPlanRepository).deleteById(1L);

        mealPlanService.deleteMealPlan(1L);

        verify(mealPlanRepository, times(1)).deleteById(1L);
    }
}
