package org.example.runner;

import lombok.RequiredArgsConstructor;
import org.example.club.Domain.entities.Club;
import org.example.player.Domain.entities.Player;
import org.example.club.Application.services.ClubService;
import org.example.player.Application.services.PlayerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ApplicationCommandLineRunner implements CommandLineRunner {
    private final ClubService clubService;
    private final PlayerService playerService;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;


        System.out.println("Welcome to the Football CLI app! Type 'help' for commands.");

        while (running) {
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "help":
                    printHelp();
                    break;

                case "list clubs":
                    List<Club> clubs = clubService.findAll();
                    clubs.stream().forEach(System.out::println);
                    break;

                case "list players":
                    Iterable<Player> players = playerService.findAll();
                    break;

                case "add player":
                    addPlayer(scanner);
                    break;

                case "add club":
                    addClub(scanner);
                    break;

                case "delete player":
                    deletePlayer(scanner);
                    break;

                case "delete club":
                    deleteClub(scanner);
                    break;

                case "exit":
                    running = false;
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Unknown command. Type 'help' for list of commands.");
            }

        }

        scanner.close();
    }

    private void printHelp() {
        System.out.println("Available commands:");
        System.out.println("help - show this help");
        System.out.println("list clubs - show all clubs");
        System.out.println("list players - show all players");
        System.out.println("add player - add a new player");
        System.out.println("delete player - delete a player by ID");
        System.out.println("exit - stop the application");
    }

    private void addPlayer(Scanner scanner) {
        System.out.print("Enter player's first name: ");
        String name = scanner.nextLine();
        System.out.print("Enter player's surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter player's age: ");
        int age = Integer.parseInt(scanner.nextLine());

        List<Club> clubs = clubService.findAll();
        System.out.println("Select club by ID:");
        clubs.forEach(c -> System.out.println(c.getId() + " - " + c.getName()));
        UUID clubId = UUID.fromString(scanner.nextLine());
        Club club = clubService.findById(clubId);
        if (club == null) {
            System.out.println("Invalid club ID. Aborting.");
            return;
        }

        Player player = Player.builder()
                .id(UUID.randomUUID())
                .name(name)
                .surname(surname)
                .age(age)
                .club(club)
                .build();

        playerService.save(player);
        System.out.println("Player added successfully!");
    }

    private void addClub(Scanner scanner) {
        System.out.print("Enter club name: ");
        String name = scanner.nextLine();
        System.out.print("Enter club country: ");
        String country = scanner.nextLine();


        Club club = Club.builder()
                .id(UUID.randomUUID())
                .name(name)
                .country(country)
                .build();

        clubService.save(club);
        System.out.println("Club added successfully!");
    }

    private void deletePlayer(Scanner scanner) {
        Iterable<Player> players = playerService.findAll();
        players.forEach(p -> System.out.println(p.getId() + " - " + p.getSurname()));
        System.out.print("Enter player ID to delete: ");
        UUID playerId = UUID.fromString(scanner.nextLine());
        Player player = playerService.findById(playerId);
        if(player == null){
            System.out.println("Invalid player ID. Aborting.");
            return;
        }
        playerService.deleteById(playerId);
        System.out.println("Player deleted successfully!");
    }

    public void deleteClub(Scanner scanner){
        List<Club> clubs = clubService.findAll();
        clubs.forEach(c -> System.out.println(c.getId() + " " + c.getName()));
        System.out.print("Enter club ID to delete: ");
        UUID clubId = UUID.fromString(scanner.nextLine());
        Club club = clubService.findById(clubId);
        if(club == null){
            System.out.println("Invalid club ID. Aborting.");
            return;
        }
        clubService.deleteById(clubId);
        System.out.println("Player deleted successfully!");
    }


}
