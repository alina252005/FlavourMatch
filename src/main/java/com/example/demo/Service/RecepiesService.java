package com.example.demo.Service;

import com.example.demo.DTO.RecipeAutocompleteResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecepiesService {

    private final WebClient webClient;
    private final WebClient.Builder webClientBuilder;
    @Value("${spoonacular.api.key}")
    private String apiKey;
    public RecepiesService(WebClient webClient, WebClient.Builder webClientBuilder) {
        this.webClient = webClient;
        this.webClientBuilder = webClientBuilder;
    }
    public Mono<String> getRecepies(int offset, int number) {
        return Mono.delay(Duration.ofSeconds(1)).then(webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/recipes/complexSearch")
                        .queryParam("apiKey", apiKey)
                        .queryParam("number", number)
                        .queryParam("offset", offset)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
        );
    }
    public List<RecipeAutocompleteResponse> autocompleteRecipes(String query,int number)
    {
        WebClient client = webClientBuilder.build();
        return client.get().uri(uriBuilder ->uriBuilder
                .scheme("https")
                .host("api.spoonacular.com")
                .path("/recipes/autocomplete")
                .queryParam("query",query)
                .queryParam("number",number)
                .queryParam("apiKey",apiKey)
                .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<RecipeAutocompleteResponse>>(){})
                .block();
    }
public Mono<String> filterRecipesByNutrients(Integer minCarb,Integer maxCarb,Integer minProtein,Integer maxProtein,Integer minCalories,Integer maxCalories,Integer minFat,Integer maxFat,int offset,int number) {
        StringBuilder url=new StringBuilder("/recipes/complexSearch?");
        url.append("apiKey=").append(apiKey);
        url.append("&offset=").append(offset);
        url.append("&number=").append(number);
        if(minCarb!=null) url.append("&minCarb=").append(minCarb);
        if(maxCarb!=null) url.append("&maxCarb=").append(maxCarb);
        if(minProtein!=null) url.append("&minProtein=").append(minProtein);
        if(maxProtein!=null) url.append("&maxProtein=").append(maxProtein);
        if(minCalories!=null) url.append("&minCalories=").append(minCalories);
        if(maxCalories!=null) url.append("&maxCalories=").append(maxCalories);
        if(maxFat!=null)url.append("&maxFat").append(maxFat);
        if(minFat!=null)url.append("&minFat").append(minFat);
        return webClient.get().uri(url.toString()).retrieve().bodyToMono(String.class);
}
public Mono<String> filterRecipesByCuisine(String cuisine,int offset,int number) {
        List<String> allowedCuisines=List.of("greek","french","italian","japanese","mexican","korean","spanish","thai");
        if(!allowedCuisines.contains(cuisine.toLowerCase()))
        {
            return Mono.error(new IllegalArgumentException("Cuisine not supported"));
        }
       StringBuilder url=new StringBuilder("/recipes/complexSearch?");
       url.append("apiKey=").append(apiKey);
       url.append("&cuisine=").append(cuisine);
       url.append("&offset=").append(offset);
       url.append("&number=").append(number);

        return webClient.get().uri(url.toString()).retrieve().bodyToMono(String.class);

}
public Mono<String> filterRecipesByType(String type,int offset,int number) {
        List<String> allowedTypes=List.of("appetizer","main course","side dish","soup","salad","dessert");
        if(!allowedTypes.contains(type.toLowerCase()))
        {
            return Mono.error(new IllegalArgumentException("Type not supported"));
        }
        StringBuilder url=new StringBuilder("/recipes/complexSearch?");
         url.append("apiKey=").append(apiKey);
         url.append("&type=").append(type);
         url.append("&offset=").append(offset);
         url.append("&number=").append(number);
         return webClient.get().uri(url.toString()).retrieve().bodyToMono(String.class);
}
public Mono<String> filterRecepiesByIntollerance(String intollerance,int offset,int number) {
        List<String>allowedIntolerances=List.of("dairy","egg","gluten","penut","seafood");
        if(!allowedIntolerances.contains(intollerance.toLowerCase()))
            {
            return Mono.error(new IllegalArgumentException("Intolerance not supported"));
            }
        StringBuilder url=new StringBuilder("/recipes/complexSearch?");
        url.append("apiKey=").append(apiKey);
        url.append("&intolerance=").append(intollerance);
        url.append("&offset=").append(offset);
         url.append("&number=").append(number);
         return webClient.get().uri(url.toString()).retrieve().bodyToMono(String.class);
}
}
