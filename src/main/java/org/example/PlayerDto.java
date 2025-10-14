package org.example;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlayerDto implements Comparable<PlayerDto>{
    private final String name;
    private final String surname;
    private final int shirtNumber;
    private final String clubName;

    @Override
    public int compareTo(PlayerDto other){
        int result = this.surname.compareToIgnoreCase(other.surname);
        if(result != 0) return result;
        result = this.name.compareToIgnoreCase(other.name);
        if(result != 0) return result;
        result = Integer.compare(this.shirtNumber, other.shirtNumber);
        if (result != 0) return result;
        return this.clubName.compareToIgnoreCase(other.clubName);
    }

    @Override
    public String toString() {
        return String.format("%s %s (nr %d, klub: %s)", name, surname, shirtNumber,
                clubName != null ? clubName : "brak");
    }
}
