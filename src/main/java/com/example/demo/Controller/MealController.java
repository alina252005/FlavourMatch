package com.example.demo.Controller;

import com.example.demo.DTO.RecipeAutocompleteResponse;
import com.example.demo.Entity.MealPlanEntity;
import com.example.demo.Repository.MealPlanRepository;
import com.example.demo.Service.RecepiesService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/meals")
public class MealController {
private final RecepiesService recepiesService;

    public MealController(RecepiesService recepiesService) {
        this.recepiesService = recepiesService;
    }

    @GetMapping("/allRecepies")
    public Mono<String> getAllRecepies(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int number) {
        return recepiesService.getRecepies(offset,number);

    }
@GetMapping("/autocomplete")
    public ResponseEntity<List<RecipeAutocompleteResponse>> autocomplete(
            @RequestParam String query,
            @RequestParam(defaultValue = "10") int  number)
{
    return  ResponseEntity.ok(recepiesService.autocompleteRecipes(query,number));
}
@GetMapping("/filterByNutrients")
    public Mono<String> filterByNutrients(
        @RequestParam(required = false)Integer minCarb,
        @RequestParam(required = false)Integer maxCarb,
        @RequestParam(required = false)Integer minProtein,
        @RequestParam(required = false)Integer maxProtein,
        @RequestParam(required = false)Integer minCalories,
        @RequestParam(required = false)Integer maxCalories,
        @RequestParam(required = false)Integer minFat,
        @RequestParam(required = false)Integer maxFat,
        @RequestParam(defaultValue = "0") int offset,
        @RequestParam(defaultValue ="10") int number

        )
{
    return recepiesService.filterRecipesByNutrients(minCarb,maxCarb,minProtein,maxProtein,minCalories,maxCalories,minFat,maxFat,offset,number);
}
@GetMapping("/filterByCuisine")
    public Mono<String> filterByCuisine(
            @RequestParam String cuisine,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int number
)
{
    return recepiesService.filterRecipesByCuisine(cuisine,offset,number);
}
@GetMapping("/filterByType")
    public Mono<String> filterByType(
            @RequestParam String type,
            @RequestParam(defaultValue ="0") int offset,
            @RequestParam(defaultValue ="10")int number

)
{
    return recepiesService.filterRecipesByType(type,offset,number);
}
@GetMapping("/filterByIntolerance")
    public Mono<String> filterByIntolerance(
            @RequestParam String intolerance,
            @RequestParam(defaultValue = "0")int offset,
            @RequestParam(defaultValue = "10") int number
)
{
    return  recepiesService.filterRecepiesByIntollerance(intolerance,offset,number);
}
}
