package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MealPlanService {
    private final WebClient webClient;
    private final WebClient.Builder webClientBuilder;
    @Value("${spoonacular.api.key}")
    private String apiKey;

    public MealPlanService(WebClient webClient, WebClient.Builder webClientBuilder) {
        this.webClient = webClient;
        this.webClientBuilder = webClientBuilder;
    }
    public Mono<String> generateMealPlan( String timeFrame,Integer targetCalories,String diet,int offset,int number) {
        List<String>supportedtimeFrames=List.of("day","week");
        List<String>supportedDiets=List.of( "gluten free", "ketogenic", "vegetarian", "lacto-vegetarian", "ovo-vegetarian",
                "vegan", "pescetarian", "paleo", "primal", "low FODMAP", "whole30");
      if(!supportedtimeFrames.contains(timeFrame.toLowerCase())) {
          return Mono.error(new IllegalArgumentException("Unsupported time frame: " ));

        }
      if(diet != null) {
          if (!supportedDiets.contains(diet.toLowerCase())) {
              return Mono.error(new IllegalArgumentException("Unsupported diet: "));
          }
      }
        StringBuilder url=new StringBuilder("https://api.spoonacular.com/mealplanner/generate?");
        url.append("apiKey=").append(apiKey);
        url.append("&timeFrame=").append(timeFrame.toLowerCase());
        if(targetCalories != null) {url.append("&targetCalories=").append(targetCalories);}
        if (diet!=null) {url.append("&diet=").append(diet.toLowerCase());
            url.append("&diet=").append(diet);
        }
        url.append("&offset=").append(offset);
        url.append("&number=").append(number);

return webClient.get().uri(url.toString()).retrieve().bodyToMono(String.class);
    }
}
