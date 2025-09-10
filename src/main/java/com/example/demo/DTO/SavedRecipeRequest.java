package com.example.demo.DTO;

import lombok.Data;

@Data
public class SavedRecipeRequest {
    private Integer externalId;
    private Integer userId;
}
