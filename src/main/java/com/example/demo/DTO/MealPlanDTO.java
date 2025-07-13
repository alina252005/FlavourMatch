package com.example.demo.DTO;

import com.example.demo.Entity.MealPlanEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealPlanDTO {
private int userid;
private int recipeid;
private LocalDate createdDate;
private boolean is_active;
public MealPlanEntity toEntity()
{
    return MealPlanEntity.builder().
        userid(userid).
        recipeid(recipeid).
        createdDate(createdDate).
        is_active(is_active).build();
}

}
