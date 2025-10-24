package org.example.service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.entity.Club;
import org.example.entity.Player;
import org.example.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final EntityManager entityManager;
    public List<Player> findAll(){
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

    public List<Player> findByClub(Club club){
        return playerRepository.findByClub(club);
    }
}
