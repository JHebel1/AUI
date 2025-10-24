package org.example.service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.entity.Club;
import org.example.repository.ClubRepository;
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

    public Club findById(UUID id){
        return clubRepository.findById(id).orElse(null);
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
}
