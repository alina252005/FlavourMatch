package com.example.demo.DTO;

import lombok.Data;

import java.util.List;

@Data
public class SavedRecipeFromMealPlanDTO {
    private List<Integer> externalId;
    private Integer userId;

}
