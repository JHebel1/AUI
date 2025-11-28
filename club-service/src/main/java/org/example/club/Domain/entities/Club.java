package org.example.club.Domain.entities;

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

    @Override
    public int compareTo(Club other) {
        int result = this.country.compareToIgnoreCase(other.country);
        if (result != 0) return result;
        return this.name.compareToIgnoreCase(other.name);
    }


}
