package org.example.player.application.services;

import org.example.core.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class ClubClientService {
    private final RestTemplate restTemplate;
    public ClubClientService(@Qualifier("club") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UUID findClub(UUID clubId) {
        try {
            return restTemplate.getForObject("/clubs/{id}" , UUID.class, clubId);
        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException();
        }
    }
}
