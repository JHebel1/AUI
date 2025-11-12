package org.example.club.Application.services;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.club.Domain.entities.Club;
import org.example.club.Domain.repositories.ClubRepository;
import org.example.core.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class ClubService {
    private final ClubRepository clubRepository;

    public List<Club> findAll(){
        return clubRepository.findAll();
    }

    public Club findById(UUID id) throws NotFoundException {
        return clubRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Club save(Club club){
        return clubRepository.save(club);
    }

    public void deleteById(UUID id){
        clubRepository.deleteById(id);
    }

    public List<Club> findByCountry(String country){
        return clubRepository.findByCountryIgnoreCase(country);
    }
    public boolean checkIfClubExists(UUID clubID){
        return clubRepository.existsById(clubID);
    }
}
