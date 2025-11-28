package org.example.player.domain.repositories;
import jakarta.transaction.Transactional;
import org.example.player.domain.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface PlayerRepository extends JpaRepository<Player, UUID>{
    List<Player> findByClub(UUID id);
    boolean existsByClubAndId(UUID clubId, UUID playerId);
    Optional<Player> findByClubAndId(UUID clubId, UUID playerId);

    @Transactional
    void deleteAllByClub(UUID clubId);
}
