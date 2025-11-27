package org.example.player.application.mappers;

import lombok.RequiredArgsConstructor;
import org.example.core.representation.CollectionRestRepresentation;
import org.example.player.application.representations.PlayerRestRepresentation;
import org.example.player.domain.entities.Player;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor

public class PlayerRestMapper {
    public PlayerRestRepresentation toRepresentation(Player player) {
        return PlayerRestRepresentation.builder()
                .id(player.getId())
                .name(player.getName())
                .surname(player.getSurname())
                .age(player.getAge())
                .club(player.getClub())
                .build();
    }


    public CollectionRestRepresentation<PlayerRestRepresentation> toRepresentation(Iterable<Player> players) {
        return CollectionRestRepresentation.<PlayerRestRepresentation>builder()
                .items(StreamSupport.stream(players.spliterator(), false)
                    .map(this::toRepresentation)
                    .toList())
                .build();
    }

    public Player toEntity(PlayerRestRepresentation playerRestRepresentation) {
        return Player.builder()
                .id(playerRestRepresentation.getId())
                .name(playerRestRepresentation.getName())
                .surname(playerRestRepresentation.getSurname())
                .age(playerRestRepresentation.getAge())
                .club(playerRestRepresentation.getClub())
                .build();
    }
    public Player toEntity(PlayerRestRepresentation representation, UUID playerID) {
        return Player.builder()
                .id(playerID)
                .name(representation.getName())
                .surname(representation.getSurname())
                .age(representation.getAge())
                .club(representation.getClub())
                .build();
    }

    public Player toEntity(PlayerRestRepresentation representation, UUID playerID, UUID clubID) {
        return Player.builder()
                .id(playerID)
                .name(representation.getName())
                .surname(representation.getSurname())
                .age(representation.getAge())
                .club(clubID)
                .build();
    }

    public void update(Player player, PlayerRestRepresentation playerRestRepresentation) {
        updateIfNotNull(playerRestRepresentation::getClub, player::setClub);
        updateIfNotNull(playerRestRepresentation::getName, player::setName);
        updateIfNotNull(playerRestRepresentation::getSurname, player::setSurname);
        updateIfNotNull(playerRestRepresentation::getAge, player::setAge);

    }

    private <T> void updateIfNotNull(Supplier<T> getter, Consumer<T> setter) {
        T value = getter.get();
        if(value != null){
            setter.accept(value);
        }
    }
}
