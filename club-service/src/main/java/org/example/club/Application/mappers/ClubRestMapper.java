package org.example.club.Application.mappers;

import org.example.club.Application.representations.ClubCreatedRequest;
import org.example.club.Application.representations.ClubRestRepresentation;
import org.example.club.Domain.entities.Club;
import org.example.core.representation.CollectionRestRepresentation;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.StreamSupport;

@Component
public class ClubRestMapper {
    public ClubRestRepresentation toRepresentation(Club club) {
        if(club == null){
            return null;
        }
        return ClubRestRepresentation.builder()
                .id(club.getId())
                .name(club.getName())
                .country(club.getCountry())
                .build();
    }


    public CollectionRestRepresentation<ClubRestRepresentation> toRepresentation(Iterable<Club> clubs) {
        return CollectionRestRepresentation.<ClubRestRepresentation>builder()
                .items(StreamSupport.stream(clubs.spliterator(), false)
                        .map(this::toRepresentation)
                        .toList())
                .build();
    }

    public Club toEntity(ClubRestRepresentation clubRestRepresentation) {
        if(clubRestRepresentation == null){
            return null;
        }
        return Club.builder()
                .id(clubRestRepresentation.getId())
                .name(clubRestRepresentation.getName())
                .country(clubRestRepresentation.getCountry())
                .build();
    }

    public Club toEntity(ClubCreatedRequest club) {
        return Club.builder()
                .id(UUID.nameUUIDFromBytes(club.name().getBytes()))
                .name(club.name())
                .country(club.country())
                .build();

    }

    public Club toEntity(ClubRestRepresentation clubRestRepresentation, UUID clubID) {
        return Club.builder()
                .id(clubID)
                .name(clubRestRepresentation.getName())
                .country(clubRestRepresentation.getCountry())
                .build();
    }

    public void update(Club club, ClubRestRepresentation clubRestRepresentation) {
        if(clubRestRepresentation ==  null){
            return;
        }
        updateIfNotNull(clubRestRepresentation::getName, club::setName);
        updateIfNotNull(clubRestRepresentation::getCountry, club::setCountry);
    }

    private <T> void updateIfNotNull(Supplier<T> getter, Consumer<T> setter) {
        T value = getter.get();
        if(value != null){
            setter.accept(value);
        }
    }
}
