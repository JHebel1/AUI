package org.example.player.infrastructure;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PlayerRestTemplateConfig {
    @Bean
    @Qualifier("player")
    public RestTemplate playerRestTemplate(RestTemplateBuilder builder, @Value("${football.player.url}") String playerUrl) {
        return builder
                .rootUri(playerUrl)
                .build();
    }
}
