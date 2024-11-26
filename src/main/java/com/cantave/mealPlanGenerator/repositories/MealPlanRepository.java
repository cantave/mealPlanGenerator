package com.cantave.mealPlanGenerator.repositories;

import com.cantave.mealPlanGenerator.models.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {
    List<MealPlan> findByUserId(Long userId);
}
