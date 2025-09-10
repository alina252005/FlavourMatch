package com.example.demo.configuration.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Configuration
public class config {
    @Bean
    public WebClient webClient(@Value("${spoonacular.api.key}") String apiKey)
    {
        return WebClient.builder()
                .baseUrl("https://api.spoonacular.com")
                .defaultHeader("x-api-key", apiKey)
                .filter((request, next) -> {
                    // LOG every request
                    System.out.println("➡️ Outgoing request: " + request.method() + " " + request.url());
                    request.headers().forEach((name, values) ->
                            values.forEach(value -> System.out.println(name + ": " + value))
                    );
                    return next.exchange(request);
                })
                .build();
    }
}
