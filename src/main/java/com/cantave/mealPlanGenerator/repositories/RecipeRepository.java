package com.cantave.mealPlanGenerator.repositories;

import com.cantave.mealPlanGenerator.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{
}
