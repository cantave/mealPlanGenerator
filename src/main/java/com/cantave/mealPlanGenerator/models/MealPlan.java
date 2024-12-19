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

    @ManyToMany
    @JoinTable(
            name = "meal_plan_breakfast",
            joinColumns = @JoinColumn(name = "meal_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> breakfast;

    @ManyToMany
    @JoinTable(
            name = "meal_plan_lunch",
            joinColumns = @JoinColumn(name = "meal_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> lunch;

    @ManyToMany
    @JoinTable(
            name = "meal_plan_dinner",
            joinColumns = @JoinColumn(name = "meal_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> dinner;

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

    public List<Recipe> getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(List<Recipe> breakfast) {
        this.breakfast = breakfast;
    }

    public List<Recipe> getLunch() {
        return lunch;
    }

    public void setLunch(List<Recipe> lunch) {
        this.lunch = lunch;
    }

    public List<Recipe> getDinner() {
        return dinner;
    }

    public void setDinner(List<Recipe> dinner) {
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
