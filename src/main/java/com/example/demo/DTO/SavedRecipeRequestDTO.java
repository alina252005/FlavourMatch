package com.example.demo.DTO;

import lombok.Data;

@Data
public class SavedRecipeRequestDTO {
    private Integer externalId;
    private Integer userId;
}
