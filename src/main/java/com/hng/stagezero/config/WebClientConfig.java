package com.hng.stagezero.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {


    @Value("${catfact.api-url}")
    private String catfactApiUrl;


    @Bean
    public WebClient catApiClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl(catfactApiUrl)
                .defaultHeader("Accept", "application/json") // Add default headers
                .build();
    }


}
