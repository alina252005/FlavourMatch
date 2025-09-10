package com.example.demo.Controller;

import com.example.demo.DTO.RecipeAutocompleteResponse;
import com.example.demo.Service.RecepiesService;
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
    }@GetMapping("/allRecepies")
    public ResponseEntity<String> getAllRecepies(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int number) {
        String result=recepiesService.getRecepies(offset, number).block();
        return  ResponseEntity.ok(result);

    }
@GetMapping("/autocomplete")
    public ResponseEntity<List<RecipeAutocompleteResponse>> autocomplete(
            @RequestParam String query,
            @RequestParam(defaultValue = "10") int  number)
{
    return  ResponseEntity.ok(recepiesService.autocompleteRecipes(query,number));
}
@GetMapping("/filterByNutrients")
    public ResponseEntity<String> filterByNutrients(
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
    String result=recepiesService.filterRecipesByNutrients(minCarb,maxCarb,minProtein,maxProtein,minCalories,maxCalories,minFat,maxFat,offset,number).block();
    return ResponseEntity.ok(result);
}
@GetMapping("/filterByCuisine")
    public ResponseEntity<String> filterByCuisine(
            @RequestParam String cuisine,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int number
)
{
    String result=recepiesService.filterRecipesByCuisine(cuisine,offset,number).block();
    return ResponseEntity.ok(result);
}
    @GetMapping("/filterByType")
    public ResponseEntity<String> filterByType(
            @RequestParam String type,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int number
    ) {
        String result = recepiesService.filterRecipesByType(type, offset, number).block();
        return ResponseEntity.ok(result);
    }
@GetMapping("/filterByIntolerance")
    public ResponseEntity<String> filterByIntolerance(
            @RequestParam String intolerance,
            @RequestParam(defaultValue = "0")int offset,
            @RequestParam(defaultValue = "10") int number
)
{
    String result=recepiesService.filterRecepiesByIntollerance(intolerance,offset,number).block();
    return ResponseEntity.ok(result);

}
}
