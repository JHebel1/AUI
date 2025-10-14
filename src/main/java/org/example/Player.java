package org.example;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
@Getter
@Builder
public class Player implements Serializable, Comparable<Player>{
    private String name;
    private String surname;
    private int shirt_number;
    @Setter
    private Club club;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Player)) return false;
        Player player = (Player) o;
        return shirt_number == player.shirt_number &&
                Objects.equals(name, player.name) &&
                Objects.equals(surname, player.surname) &&
                Objects.equals(club != null ? club.getName() : null,
                    player.club != null ? player.club.getName() : null);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, surname, shirt_number,
                club != null ? club.getName() : null);
    }

    @Override
    public int compareTo(Player p){
        int result = this.surname.compareToIgnoreCase(p.surname);
        if (result != 0) return result;

        result = this.name.compareToIgnoreCase(p.name);
        if (result != 0) return result;


        result = Integer.compare(this.shirt_number, p.shirt_number);
        if (result != 0) return result;

        return (this.club != null && p.club != null) ?
                this.club.getName().compareToIgnoreCase(p.club.getName()) : 0;
    }

    @Override
    public String toString() {
        return String.format("%s %s (nr %d, klub: %s)",
                name,
                surname,
                shirt_number,
                club != null ? club.getName() : "brak");
    }
}
