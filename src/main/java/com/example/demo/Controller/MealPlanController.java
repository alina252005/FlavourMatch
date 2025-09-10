package com.example.demo.Controller;

import com.example.demo.Service.MealPlanService;
import io.jsonwebtoken.RequiredTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/mealplan")
public class MealPlanController {
   private final MealPlanService mealPlanService;

    public MealPlanController(MealPlanService mealPlanService) {
        this.mealPlanService = mealPlanService;
    }
    @GetMapping("/generateMealPlan")
    public ResponseEntity<String> generateMealPlan(
            @RequestParam(required = true) String timeFrame,
            @RequestParam(required = false) Integer targetCalories,
            @RequestParam(required = false) String diet,
            @RequestParam(defaultValue = "0")int offset,
            @RequestParam(defaultValue = "10") int number
    )
    {
      String result=mealPlanService.generateMealPlan(timeFrame,targetCalories,diet,offset,number).block();
      return ResponseEntity.ok().body(result);
    }
}
