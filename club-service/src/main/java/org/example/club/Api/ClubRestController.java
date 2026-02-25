package org.example.club.Api;

import lombok.RequiredArgsConstructor;
import org.example.club.Application.mappers.ClubRestMapper;
import org.example.club.Application.representations.ClubCreatedRequest;
import org.example.club.Application.representations.ClubRestRepresentation;
import org.example.club.Application.services.ClubService;
import org.example.club.Domain.entities.Club;
import org.example.core.exceptions.NotFoundException;
import org.example.core.representation.CollectionRestRepresentation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class ClubRestController {
    private final ClubService clubService;
    private final ClubRestMapper clubMapper;

    @GetMapping(
            path = "/api/clubs",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CollectionRestRepresentation<ClubRestRepresentation>> getClubs(){
        Iterable<Club> clubs = clubService.findAll();
        return ResponseEntity.ok(clubMapper.toRepresentation(clubs));
    }

    @GetMapping(
            path = "/api/clubs/{clubID}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ClubRestRepresentation> getClubById(@PathVariable("clubID") UUID clubID){
        try {
            Club club = clubService.findById(clubID);
            return ResponseEntity.ok(clubMapper.toRepresentation(club));
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(
            path = "/api/clubs",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ClubRestRepresentation> addClub(@RequestBody ClubCreatedRequest club){
        Club newClub = clubMapper.toEntity(club);
        clubService.save(newClub);
        return ResponseEntity.ok(clubMapper.toRepresentation(newClub));
    }

    @PutMapping(
            path = "/api/clubs/{clubID}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> putClub(@PathVariable("clubID") UUID clubID, @RequestBody ClubRestRepresentation club){
        if(!clubService.checkIfClubExists(clubID)){
            clubService.save(clubMapper.toEntity(club, clubID));
            return ResponseEntity.created(URI.create("/api/clubs/"+clubID)).build();
        } else {
            Club clubToUpdate = clubService.findById(clubID);
            clubMapper.update(clubToUpdate, club);
            clubService.save(clubToUpdate);
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/api/clubs/{clubID}")
    public ResponseEntity<Void> deleteClub(@PathVariable("clubID") UUID clubID){
        try {
            clubService.deleteById(clubID);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
