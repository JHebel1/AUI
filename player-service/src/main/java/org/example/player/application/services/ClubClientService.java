package org.example.player.application.services;

import org.example.club.domain.ClubRestRepresentation;
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
            ClubRestRepresentation club = restTemplate.getForObject("/clubs/{id}" , ClubRestRepresentation.class, clubId);

            return club.getId();
        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException();
        }
    }
}
