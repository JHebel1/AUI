package org.example.player.application.services;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.example.core.exceptions.NotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.player.domain.entities.Player;
import org.example.player.domain.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final EntityManager entityManager;
    private final ClubClientService clubClientService;

    public Iterable<Player> findAll(){
        return playerRepository.findAll();
    }


    public Player findById(UUID id){
        return playerRepository.findById(id).orElseThrow(NotFoundException::new);
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
        return playerRepository.findByClub(id);
    }

    public boolean checkIfPlayerExists(UUID id){
        return playerRepository.existsById(id);
    }
    public boolean checkIfPlayerExists(UUID playerId, UUID clubId){
        clubClientService.findClub(clubId);
        return playerRepository.existsByClubAndId(clubId, playerId);
    }

    public Player findPlayer(UUID clubId, UUID playerId){
        clubClientService.findClub(clubId);
        return playerRepository.findByClubAndId(clubId, playerId).orElseThrow(NotFoundException::new);
    }

    public void deletePlayers(UUID clubId){
        //clubClientService.findClub(clubId);
        playerRepository.deleteAllByClub(clubId);
    }
}
