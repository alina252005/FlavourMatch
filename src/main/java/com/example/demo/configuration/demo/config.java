package com.example.demo.configuration.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class config {
    @Bean
    public WebClient webClient()
    {
        return WebClient.builder()
                        .baseUrl("https://api.spoonacular.com")
                        .build();
    }
}
