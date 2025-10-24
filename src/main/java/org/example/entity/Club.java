package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode
@ToString
@Table(name = "clubs")
public class Club implements Serializable, Comparable<Club>{

    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "club", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Builder.Default
    private List <Player> players = new ArrayList<>();

    @Override
    public int compareTo(Club other) {
        int result = this.country.compareToIgnoreCase(other.country);
        if (result != 0) return result;
        return this.name.compareToIgnoreCase(other.name);
    }


}
