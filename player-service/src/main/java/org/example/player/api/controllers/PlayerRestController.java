package org.example.player.api.controllers;

import lombok.RequiredArgsConstructor;
import org.example.core.exceptions.NotFoundException;
import org.example.player.application.mappers.PlayerRestMapper;
import org.example.player.application.representations.PlayerRestRepresentation;
import org.example.player.application.services.PlayerService;
import org.example.player.domain.entities.Player;
import org.example.core.representation.CollectionRestRepresentation;
import org.example.player.domain.repositories.PlayerRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class PlayerRestController {
    private final PlayerService playerService;
    private final PlayerRestMapper playerMapper;
    private final PlayerRepository playerRepository;

    @GetMapping(
            path = "/api/players",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionRestRepresentation<PlayerRestRepresentation>> getPlayers() {
        Iterable<Player> players = playerService.findAll();
        return ResponseEntity.ok(playerMapper.toRepresentation(players));
    }

    @GetMapping(
            path = "/api/clubs/{clubID}/players",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CollectionRestRepresentation<PlayerRestRepresentation>> getPlayersByClub(@PathVariable("clubID") UUID clubID) {
        try {
            Iterable<Player> players = playerService.findByClub(clubID);
            return ResponseEntity.ok(playerMapper.toRepresentation(players));
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(
            path = "/api/players/{playerID}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PlayerRestRepresentation> getPlayer(@PathVariable("playerID") UUID playerID) {
        try {
            Player player = playerService.findById(playerID);
            return ResponseEntity.ok(playerMapper.toRepresentation(player));
        }catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(
            value = "/api/players/{playerID}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> putPlayer(@PathVariable("playerID") UUID playerID, @RequestBody PlayerRestRepresentation player) {
        if(!playerService.checkIfPlayerExists(playerID)){
            playerService.save(playerMapper.toEntity(player, playerID));
            return ResponseEntity.created(URI.create("/api/players/" +  playerID)).build();
        } else {
            Player newPlayer = playerService.findById(playerID);
            playerMapper.update(newPlayer, player);
            playerService.save(newPlayer);
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(
            value = "/api/clubs/{clubID}/players",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> addPlayerClub(@PathVariable("clubID") UUID clubID, @RequestBody PlayerRestRepresentation player) {
        UUID playerID = UUID.nameUUIDFromBytes((player.getName().toLowerCase() + "_" + player.getSurname().toLowerCase()).getBytes());
        playerService.save(playerMapper.toEntity(player, playerID, clubID));
        return ResponseEntity.created(URI.create("/api/clubs/" +  clubID + "/players/" + playerID)).build();
    }

    @PutMapping(
            value = "/api/clubs/{clubID}/players/{playerID}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> PutClubPlayer(@PathVariable("clubID") UUID clubID, @PathVariable("playerID") UUID playerID, @RequestBody PlayerRestRepresentation player) {
        try{
            if(!playerService.checkIfPlayerExists(playerID, clubID)){
                playerService.save(playerMapper.toEntity(player, playerID, clubID));
                return ResponseEntity.created(URI.create("/api/clubs/" +  clubID + "/players/" + playerID)).build();
            } else {
                Player newPlayer = playerService.findPlayer(clubID, playerID);
                playerMapper.update(newPlayer, player);
                playerService.save(newPlayer);
                return ResponseEntity.noContent().build();
            }
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/players/{playerID}")
    public ResponseEntity<Void> deletePlayer(@PathVariable("playerID") UUID playerID) {
        try {
            playerService.deleteById(playerID);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/clubs/{clubID}/players")
    public ResponseEntity<Void> deleteClubPlayers(@PathVariable("clubID") UUID clubID) {
        try {
            playerService.deletePlayers(clubID);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
