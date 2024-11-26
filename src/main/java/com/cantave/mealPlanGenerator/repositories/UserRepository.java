package com.cantave.mealPlanGenerator.repositories;

import com.cantave.mealPlanGenerator.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
