package com.example.demo.DTO;

import com.example.demo.Entity.JournalEntryEntity;
import com.example.demo.Entity.MealType;
import com.example.demo.Entity.UserEntity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JournalEntryDto implements Serializable {
    private Long id;
    private MealType mealtype;
    private Integer calories;
    private Integer proteins;
    private Integer fats;
    private Integer carbs;
    private Double quantity;
    private Integer recipeid;
    private String customFoodName;
    private LocalDate date;
    private UserEntity user;

public JournalEntryEntity toEntity() {
    return JournalEntryEntity.builder()
                    .id(id)
                    .mealType(mealtype)
                    .calories(calories)
                    .proteins(proteins)
                    .fats(fats)
                    .carbs(carbs)
                    .quantity(quantity)
                    .recipeId(recipeid)
                    .customFoodName(customFoodName)
                    .date(date)
                    .user(user)
                    .build();
}

}
