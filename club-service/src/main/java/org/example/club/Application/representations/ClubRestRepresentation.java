package org.example.club.Application.representations;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode

public class ClubRestRepresentation {
    private UUID id;
    private String name;
    private String country;
}
