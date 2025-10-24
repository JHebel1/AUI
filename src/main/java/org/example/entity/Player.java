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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "players")
public class Player implements Serializable, Comparable<Player>{

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private int age;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "club_id")
    private Club club;

    @Override
    public String toString(){
        return "Player{name= " + name + " surname = " + surname + " club= " + club.getName();
    }

    @Override
    public int compareTo(Player p){
        int result = this.surname.compareToIgnoreCase(p.surname);
        if (result != 0) return result;

        result = this.name.compareToIgnoreCase(p.name);
        if (result != 0) return result;


        result = Integer.compare(this.age, p.age);
        if (result != 0) return result;

        return (this.club != null && p.club != null) ?
                this.club.getName().compareToIgnoreCase(p.club.getName()) : 0;
    }
}
