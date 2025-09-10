package com.example.demo.Controller;

import com.example.demo.DTO.SavedRecipeRequest;
import com.example.demo.Entity.SavedRecipesEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.SavedRecipesRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.JournalService;
import com.example.demo.Service.MealPlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<SavedRecipesEntity>saveRecipe(@RequestBody SavedRecipeRequest request) {
        UserEntity user=userRepository.findById(request.getUserId())
                .orElseThrow(()->new RuntimeException("user not found"));
        SavedRecipesEntity saved=new SavedRecipesEntity();
        saved.setExternalId(request.getExternalId());
        saved.setUser(user);
        SavedRecipesEntity savedEntity=savedRecipesRepository.save(saved);
        return ResponseEntity.ok(savedEntity);

    }
}
