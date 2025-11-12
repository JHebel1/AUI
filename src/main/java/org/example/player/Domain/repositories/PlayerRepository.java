package org.example.player.Domain.repositories;
import org.example.club.Domain.entities.Club;
import org.example.player.Domain.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface PlayerRepository extends JpaRepository<Player, UUID>{
    List<Player> findByClubId(UUID id);
    boolean existsByClubAndId(Club club, UUID playerId);
    Optional<Player> findByClubAndId(Club club, UUID playerId);
}
