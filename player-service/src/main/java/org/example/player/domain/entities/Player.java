package org.example.player.domain.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode()
@Table(name = "players")
public class Player{

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private int age;

    @Column(name = "club")
    private UUID club;

    @Override
    public String toString(){
        return "Player{name= " + name + " surname = " + surname + " club= " + club;
    }


}
