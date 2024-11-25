package com.cantave.mealPlanGenerator.repositories;

import com.cantave.mealPlanGenerator.models.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {
}
