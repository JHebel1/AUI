package org.example.player.Application.representations;

import lombok.*;
import org.example.club.Application.representations.ClubRestRepresentation;

import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode


public class PlayerRestRepresentation {
    private UUID id;
    private String name;
    private String surname;
    private int age;
    private ClubRestRepresentation club;
}
