package com.example.demo.Controller;

import com.example.demo.DTO.MealPlanRequest;
import com.example.demo.DTO.MealPlanResponseDTO;
import com.example.demo.DTO.SavedRecipeFromMealPlanDTO;
import com.example.demo.DTO.SavedRecipeRequestDTO;
import com.example.demo.Entity.SavedRecipesEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.SavedRecipesRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.JournalService;
import com.example.demo.Service.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Favorite")
public class SavedRecipesController {
    private final SavedRecipesRepository savedRecipesRepository;
    private final UserRepository userRepository;


    public SavedRecipesController(SavedRecipesRepository savedRecipesRepository, JournalService journalService, UserRepository userRepository) {
        this.savedRecipesRepository = savedRecipesRepository;

        this.userRepository = userRepository;
    }
    @PostMapping("/SaveButtonFromRecipe")
    public ResponseEntity<SavedRecipesEntity> saveRecipe(@RequestBody SavedRecipeRequestDTO request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UserEntity user = userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new RuntimeException("user not found"));
        SavedRecipesEntity saved=new SavedRecipesEntity();
        saved.setExternalId(request.getExternalId());
        saved.setUser(user);
        SavedRecipesEntity savedEntity=savedRecipesRepository.save(saved);
        return ResponseEntity.ok(savedEntity);

    }

    @PostMapping("/SaveButtonFromMealPlan")
    public ResponseEntity<List<SavedRecipesEntity>> saveFromMealPlan(
            @RequestBody MealPlanRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UserEntity user = userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new RuntimeException("user not found"));
        List<MealPlanResponseDTO.MealDTO> meals;
        if (request.getDailyMeals() != null && !request.getDailyMeals().isEmpty()) {
            meals = request.getDailyMeals();

        } else if (request.getWeek() != null) {
            meals = new ArrayList<>();
            List<MealPlanResponseDTO.DayDTO> days = List.of(
                    request.getWeek().getMonday(),
                    request.getWeek().getTuesday(),
                    request.getWeek().getWednesday(),
                    request.getWeek().getThursday(),
                    request.getWeek().getFriday(),
                    request.getWeek().getSaturday(),
                    request.getWeek().getSunday()
            );
            for (MealPlanResponseDTO.DayDTO day : days) {
                if (day != null && day.getMeals() != null) {
                    meals.addAll(day.getMeals());
                }
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
        List<SavedRecipesEntity> savedList = meals.stream().map(meal -> {
            SavedRecipesEntity saved = new SavedRecipesEntity();
            saved.setExternalId(meal.getId());
            saved.setUser(user);
            return savedRecipesRepository.save(saved);
        }).toList();
        return ResponseEntity.ok(savedList);

    }
}
