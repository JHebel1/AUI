package org.example.core.representation;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode

public class CollectionRestRepresentation<T> {
    @Singular
    private List<T> items;
}
