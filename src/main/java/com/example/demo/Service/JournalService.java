package com.example.demo.Service;

import com.example.demo.DTO.RecipesNutritionDto;
import com.example.demo.Entity.JournalEntryEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.JournalRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class JournalService {
    @Autowired
    private JournalRepository journalRepository;
    @Autowired
    private UserRepository userRepository;
    private final WebClient webClient;
    private final WebClient.Builder webClientBuilder;
    private final String base_url="https://api.spoonacular.com";
    @Value("${spoonacular.api.key}")
    private String apiKey;

    public JournalService(WebClient webClient, WebClient.Builder webClientBuilder) {
        this.webClient = webClient;
        this.webClientBuilder = webClientBuilder;
    }

    public ResponseEntity<String> addManualEntry(JournalEntryEntity entry, String username) {
        UserEntity user =userRepository.findUserEntitiesByUsername(username);

        if(user==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
     entry.setUser(user);
        journalRepository.save(entry);
        return ResponseEntity.status(HttpStatus.CREATED).build();


    }
    public RecipesNutritionDto getNutritionFromAPi(Integer externalId)
    {
        return webClient.get()
                .uri(base_url+"/recipes/{id}/information?apiKey={apiKey}&includeNutrition=true",externalId,apiKey)
                .retrieve()
                .bodyToMono(RecipesNutritionDto.class)
                .block();
    }
    public int parseNutritionValue(RecipesNutritionDto.Nutrition nutrition,String nutrientName) {
        if(nutrition==null || nutrition.getNutrients()==null) return 0;
        return nutrition.getNutrients().stream()
                .filter(n->n.getName().equalsIgnoreCase(nutrientName))
                .findFirst()
                .map(n->n.getAmount().intValue())
                .orElse(0);
    }
}

