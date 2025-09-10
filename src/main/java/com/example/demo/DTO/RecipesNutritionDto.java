package com.example.demo.DTO;

import lombok.Data;

import java.util.List;

@Data
public class RecipesNutritionDto {
    private String title; // titlu rețetă
    private Nutrition nutrition;

    @Data
    public static class Nutrition {
        private List<Nutrient> nutrients;
    }

    @Data
    public static class Nutrient {
        private String name;   // ex: "Calories", "Protein", "Fat", "Carbohydrates"
        private Double amount; // ex: 200.0
        private String unit;   // ex: "kcal", "g"
    }
}
