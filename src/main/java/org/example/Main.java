package org.example;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import static org.example.Player.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("ZADANIE 2");
        System.out.println("-----------------------------");
        Club barca = Club.builder()
                .name("FC Barcelona")
                .country("Spain")
                .build();

        Club real = Club.builder()
                .name("Real Madrid")
                .country("Spain")
                .build();

        Club juventus = Club.builder()
                .name("Juventus")
                .country("Italy")
                .build();

        Player messi = builder().name("Lionel").surname("Messi").shirt_number(10).build();
        Player iniesta = builder().name("Andres").surname("Iniesta").shirt_number(8).build();
        Player ramos = Player.builder().name("Sergio").surname("Ramos").shirt_number(4).build();
        Player mbappe = Player.builder().name("Kylian").surname("Mbappe").shirt_number(10).build();
        Player vinicius = Player.builder().name("Vinicius").surname("Junior").shirt_number(8).build();
        Player ronaldo = Player.builder().name("Cristiano").surname("Ronaldo").shirt_number(7).build();

        barca.addPlayer(messi);
        barca.addPlayer(iniesta);

        real.addPlayer(ramos);
        real.addPlayer(ronaldo);
        real.addPlayer(mbappe);
        real.addPlayer(vinicius);

        List<Club> clubs = List.of(barca, real, juventus);


        clubs.forEach(club -> {
            System.out.println(club);
        });
        System.out.println("-----------------------------");
        System.out.println();

        System.out.println("ZADANIE 3");
        System.out.println("-----------------------------");

        Set<Player> playerSet = clubs.stream()
                .flatMap(club -> club.getPlayers().stream())
                .collect(Collectors.toSet());

        playerSet.stream().forEach(System.out::println);

        System.out.println("-----------------------------");
        System.out.println();

        System.out.println("ZADANIE 4");
        System.out.println("-----------------------------");

        playerSet.stream().filter(p -> p.getClub().getName().equals("FC Barcelona"))
                        .sorted(Comparator.comparingInt(Player::getShirt_number))
                        .forEach(System.out::println);

        System.out.println("-----------------------------");
        System.out.println();

        System.out.println("ZADANIE 5");
        System.out.println("-----------------------------");

        List<PlayerDto> playerDtos = playerSet.stream()
                        .map(Mapper::toDto)
                        .sorted()
                        .toList();

        playerDtos.stream().forEach(System.out::println);

        System.out.println("-----------------------------");
        System.out.println();

        System.out.println("ZADANIE 6");
        System.out.println("-----------------------------");

        Files.saveClubsToFile(clubs, "clubs.bin");
        List<Club> loadedClubs = Files.readClubsFromFile("clubs.bin");
        if(loadedClubs != null){
            loadedClubs.forEach(club -> {
                System.out.println(club);
            });
        }

        System.out.println("-----------------------------");
        System.out.println();

        System.out.println("ZADANIE 7");
        System.out.println("-----------------------------");

        ForkJoinPool customPool = new ForkJoinPool(6);

        try{
            customPool.submit(() -> {
                clubs.parallelStream().forEach(club -> {
                    System.out.println(club + " " + Thread.currentThread().getName());

                    club.getPlayers().forEach(player -> {
                        try {
                            Thread.sleep(500);
                            System.out.println(player + " " + Thread.currentThread().getName());
                        } catch (InterruptedException e){
                            Thread.currentThread().interrupt();
                        }
                    });
                });
            }).get();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            customPool.shutdown();
        }


        System.out.println("-----------------------------");
        System.out.println();
    }
}