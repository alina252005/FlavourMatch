package com.example.demo.Repository;

import com.example.demo.Entity.MealPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealPlanRepository extends JpaRepository<MealPlanEntity,Integer> {

}
