package org.example.player.application.representations;

import java.util.UUID;

public record PlayerCreatedRequest (
        String name,
        String surname,
        int age,
        UUID club
){}
