package org.example;

public class Mapper {
    public static PlayerDto toDto(Player player){
        return PlayerDto.builder()
                .name(player.getName())
                .surname(player.getSurname())
                .shirtNumber(player.getShirt_number())
                .clubName(player.getClub() != null ? player.getClub().getName() : null)
                .build();
    }

}
