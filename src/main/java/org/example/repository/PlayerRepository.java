package org.example.repository;
import org.example.entity.Club;
import org.example.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;
public interface PlayerRepository extends JpaRepository<Player, UUID>{
    List<Player> findByClub(Club club);
}
