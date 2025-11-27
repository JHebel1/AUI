package org.example.club.infrastructure;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;

@Configuration
public class ClubRestTemplateConfig {
    @Bean
    @Qualifier("club")
    public RestTemplate clubRestTemplate(RestTemplateBuilder builder) {
        return builder
                .rootUri("http://localhost:8081/api")
                .build();
    }
}
