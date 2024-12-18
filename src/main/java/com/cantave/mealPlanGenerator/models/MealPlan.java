package com.cantave.mealPlanGenerator.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "meal_plans")
public class MealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @ElementCollection
    private List<String> breakfast;

    @ElementCollection
    private List<String> lunch;

    @ElementCollection
    private List<String> dinner;

    private Date date;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "meal_plan_recipes",
            joinColumns = @JoinColumn(name = "meal_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> recipes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<String> getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(List<String> breakfast) {
        this.breakfast = breakfast;
    }

    public List<String> getLunch() {
        return lunch;
    }

    public void setLunch(List<String> lunch) {
        this.lunch = lunch;
    }

    public List<String> getDinner() {
        return dinner;
    }

    public void setDinner(List<String> dinner) {
        this.dinner = dinner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
