package org.example.player.Application.services;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.example.club.Application.services.ClubService;
import org.example.core.exceptions.NotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.club.Domain.entities.Club;
import org.example.player.Domain.entities.Player;
import org.example.player.Domain.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final EntityManager entityManager;
    private final ClubService clubService;

    public Iterable<Player> findAll(){
        return playerRepository.findAll();
    }


    public Player findById(UUID id){
        return playerRepository.findById(id).orElse(null);
    }

    public Player save(Player player){
        return playerRepository.save(player);
    }

    public void deleteById(UUID id){
        Player playerToDelete = playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Player not found"));
        playerRepository.delete(playerToDelete);
    }

    public Iterable<Player> findByClub(UUID id){
        return playerRepository.findByClubId(id);
    }

    public boolean checkIfPlayerExists(UUID id){
        return playerRepository.existsById(id);
    }
    public boolean checkIfPlayerExists(UUID playerId, UUID clubId){
        Club club = clubService.findById(clubId);
        return playerRepository.existsByClubAndId(club, playerId);
    }

    public Player findPlayer(UUID clubId, UUID playerId){
        Club club = clubService.findById(clubId);
        return playerRepository.findByClubAndId(club, playerId).orElseThrow(NotFoundException::new);
    }
}
