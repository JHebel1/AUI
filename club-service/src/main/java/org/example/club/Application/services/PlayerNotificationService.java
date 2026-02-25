package org.example.club.Application.services;

import org.example.club.Application.events.ClubDeletedEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PlayerNotificationService {
    private final RestTemplate restTemplate;
    public PlayerNotificationService(@Qualifier("player") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @EventListener
    public void onClubDeleted(ClubDeletedEvent event){
        restTemplate.delete("/api/clubs/{clubId}/players", event.getClubID());
    }
}
