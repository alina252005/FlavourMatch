package com.example.demo.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MealPlanRequest {
    private Integer userId;
    private List<MealPlanResponseDTO.MealDTO> dailyMeals;
    private MealPlanResponseDTO.WeekDTO week;
}
