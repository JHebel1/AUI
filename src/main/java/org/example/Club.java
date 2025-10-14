package org.example;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class Club implements Serializable, Comparable<Club>{
    private String name;
    private String country;
    @Builder.Default
    private List<Player> players = new ArrayList<>();

    public void addPlayer(Player player){
        players.add(player);
        player.setClub(this);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Club)) return false;
        Club club = (Club) o;
        return Objects.equals(name, club.name) &&
                Objects.equals(country, club.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }

    @Override
    public int compareTo(Club other) {
        int result = this.country.compareToIgnoreCase(other.country);
        if (result != 0) return result;
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Club{name='").append(name)
                .append("', country='").append(country)
                .append("', players=[");
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            sb.append(p.getName()).append(" ").append(p.getSurname());
            if (i < players.size() - 1) sb.append(", ");
        }
        sb.append("]}");
        return sb.toString();
    }
}
