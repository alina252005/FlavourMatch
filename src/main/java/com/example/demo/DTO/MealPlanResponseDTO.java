package com.example.demo.DTO;

import lombok.Data;

import java.util.List;

public class MealPlanResponseDTO {
    @Data
    public static class MealPlanDTO {
        private WeekDTO week;
        private Integer userId; // îl trimiți din frontend/Postman
    }

    @Data
    public static class WeekDTO {
        private DayDTO monday;
        private DayDTO tuesday;
        private DayDTO wednesday;
        private DayDTO thursday;
        private DayDTO friday;
        private DayDTO saturday;
        private DayDTO sunday;
    }

    @Data
    public static class DayDTO {
        private List<MealDTO> meals;
        private NutrientsDTO nutrients;
    }

    @Data
    public static class MealDTO {
        private Integer id;
        private String title;
        private String image;
        private String imageType;
        private Integer readyInMinutes;
        private Integer servings;
        private String sourceUrl;
    }

    @Data
    public static class NutrientsDTO {
        private Double calories;
        private Double protein;
        private Double fat;
        private Double carbohydrates;
    }

}
