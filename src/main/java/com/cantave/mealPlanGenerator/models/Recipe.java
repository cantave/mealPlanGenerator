package com.cantave.mealPlanGenerator.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private String category;

    @Column(nullable = true)
    private String area;

    @Column(nullable = true)
    private String mealThumb;

    @Column(nullable = true)
    private String youtube;

    @ElementCollection
    @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "ingredient")
    private List<String> ingredients;

    @ElementCollection
    @CollectionTable(name = "recipe_measures", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "measure")
    private List<String> measures;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String instructions;

    public Recipe() {
    }

    @JsonCreator
    public Recipe(
            @JsonProperty("id") Long id,
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("category") String category,
            @JsonProperty("area") String area,
            @JsonProperty("mealThumb") String mealThumb,
            @JsonProperty("youtube") String youtube,
            @JsonProperty("ingredients") List<String> ingredients,
            @JsonProperty("measures") List<String> measures,
            @JsonProperty("instructions") String instructions
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.area = area;
        this.mealThumb = mealThumb;
        this.youtube = youtube;
        this.ingredients = ingredients;
        this.measures = measures;
        this.instructions = instructions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMealThumb() {
        return mealThumb;
    }

    public void setMealThumb(String mealThumb) {
        this.mealThumb = mealThumb;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public List<String> getMeasures() {
        return measures;
    }

    public void setMeasures(List<String> measures) {
        this.measures = measures;
    }
}
