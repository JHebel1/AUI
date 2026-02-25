package org.example.club.Domain.repositories;
import org.example.club.Domain.entities.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClubRepository extends JpaRepository<Club, UUID>{
    List<Club> findByCountryIgnoreCase(String country);
}
