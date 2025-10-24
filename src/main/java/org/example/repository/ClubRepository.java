package org.example.repository;
import org.example.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClubRepository extends JpaRepository<Club, UUID>{
    List<Club> findByCountryIgnoreCase(String country);
}
