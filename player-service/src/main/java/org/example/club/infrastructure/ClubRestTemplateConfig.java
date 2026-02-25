package org.example.club.infrastructure;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;

@Configuration
public class ClubRestTemplateConfig {
    @Bean
    @Qualifier("club")
    public RestTemplate clubRestTemplate(RestTemplateBuilder builder, @Value("${football.club.url}") String clubUrl) {
        return builder
                .rootUri(clubUrl)
                .build();
    }
}
