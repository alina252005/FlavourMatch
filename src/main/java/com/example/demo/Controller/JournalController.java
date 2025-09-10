package com.example.demo.Controller;

import com.example.demo.DTO.RecipesNutritionDto;
import com.example.demo.Entity.JournalEntryEntity;
import com.example.demo.Entity.MealType;
import com.example.demo.Entity.SavedRecipesEntity;
import com.example.demo.Repository.JournalRepository;
import com.example.demo.Repository.SavedRecipesRepository;
import com.example.demo.Service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.time.LocalDate;

@RestController
@RequestMapping("/journal")
public class JournalController {

   private final JournalService journalService;
   private final JournalRepository journalRepository;
   private final SavedRecipesRepository savedRecipesRepository;

    public JournalController(JournalService journalService, JournalRepository journalRepository, SavedRecipesRepository savedRecipesRepository) {
        this.journalService = journalService;
        this.journalRepository = journalRepository;
        this.savedRecipesRepository = savedRecipesRepository;
    }


    @PostMapping("/addManualEntry")
    public ResponseEntity<String> addManualEntry(@RequestBody JournalEntryEntity entry,Principal principal) {
        String username = principal.getName();
        return journalService.addManualEntry(entry, username);
    }
@PostMapping("/from-saved/{savedRecipeId}")
    public ResponseEntity<JournalEntryEntity> fromSaved(@PathVariable Integer savedRecipeId, @RequestParam MealType mealType, @RequestParam LocalDate localDate,@RequestParam Double quantity) {
    SavedRecipesEntity saved=savedRecipesRepository.findById(savedRecipeId)
            .orElseThrow(()->new RuntimeException("Saved recipe not found"));
    RecipesNutritionDto recipesNutritionDto=journalService.getNutritionFromAPi(saved.getExternalId());
  int calories=journalService.parseNutritionValue(recipesNutritionDto.getNutrition(),"Calories");
  int protein=journalService.parseNutritionValue(recipesNutritionDto.getNutrition(),"Protein");
  int fats=journalService.parseNutritionValue(recipesNutritionDto.getNutrition(),"Fat");
  int carbs=journalService.parseNutritionValue(recipesNutritionDto.getNutrition(),"Carbohydrates");
  JournalEntryEntity entry=JournalEntryEntity.builder()
          .customFoodName(recipesNutritionDto.getTitle())
          .mealType(mealType)
          .calories(calories)
          .proteins(protein)
          .fats(fats)
          .carbs(carbs)
          .quantity(quantity)
          .recipeId(saved.getExternalId())
          .date(localDate)
          .user(saved.getUser())
          .build();
  JournalEntryEntity savedEntry=journalRepository.save(entry);
  return ResponseEntity.ok(savedEntry);
}
}
