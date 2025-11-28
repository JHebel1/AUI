package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder,
                                     @Value("${player.service.url}") String playerUrl,
                                     @Value("${club.service.url}") String clubUrl,
                                     @Value("${api.gateway.host}") String host)
    {
        return builder
                .routes()
                .route("clubs", route -> route
                        .host(host)
                        .and()
                        .path(
                                "/api/clubs/{uuid}",
                                "/api/clubs"
                        )
                        .uri(clubUrl)
                )
                .route("players", route -> route
                        .host(host)
                        .and()
                        .path(
                                "/api/players",
                                "/api/players/**",
                                "/api/clubs/{uuid}/players",
                                "/api/clubs/{uuid}/players/**"
                        )
                        .uri(playerUrl)
                )
                .build();
    }
}
