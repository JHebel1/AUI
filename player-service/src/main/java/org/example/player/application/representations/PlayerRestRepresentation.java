package org.example.player.application.representations;

import lombok.*;
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
    private UUID club;
}
