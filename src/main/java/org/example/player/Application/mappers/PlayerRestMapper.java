package org.example.player.Application.mappers;

import lombok.RequiredArgsConstructor;
import org.example.club.Application.mappers.ClubRestMapper;
import org.example.club.Domain.entities.Club;
import org.example.core.representation.CollectionRestRepresentation;
import org.example.player.Application.representations.PlayerRestRepresentation;
import org.example.player.Domain.entities.Player;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor

public class PlayerRestMapper {
    private final ClubRestMapper clubMapper;
    public PlayerRestRepresentation toRepresentation(Player player) {
        return PlayerRestRepresentation.builder()
                .id(player.getId())
                .name(player.getName())
                .surname(player.getSurname())
                .age(player.getAge())
                .club(clubMapper.toRepresentation(player.getClub()))
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
                .club(clubMapper.toEntity(playerRestRepresentation.getClub()))
                .build();
    }
    public Player toEntity(PlayerRestRepresentation representation, UUID playerID) {
        return Player.builder()
                .id(playerID)
                .name(representation.getName())
                .surname(representation.getSurname())
                .age(representation.getAge())
                .club(clubMapper.toEntity(representation.getClub()))
                .build();
    }

    public Player toEntity(PlayerRestRepresentation representation, UUID playerID, UUID clubID) {
        return Player.builder()
                .id(playerID)
                .name(representation.getName())
                .surname(representation.getSurname())
                .age(representation.getAge())
                .club(Club.builder()
                        .id(clubID)
                        .build())
                .build();
    }

    public void update(Player player, PlayerRestRepresentation playerRestRepresentation) {
        clubMapper.update(player.getClub(), playerRestRepresentation.getClub());
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
