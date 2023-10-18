package com.example.sportsproyect.service;

import com.example.sportsproyect.model.Stadium;
import com.example.sportsproyect.repository.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StadiumService {
    private final StadiumRepository stadiumRepository;
    @Autowired
    public StadiumService(StadiumRepository stadiumRepository)
    {
        this.stadiumRepository = stadiumRepository;
    }
    public List<Stadium> getAllStadiums() {
        return stadiumRepository.findAll();
    }
    public Stadium getStadiumById(long id) {
        return stadiumRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Match not found"));
    }
    public Stadium createStadium(Stadium player) {
        return stadiumRepository.save(player);
    }
    public Stadium updateStadium(Stadium player) {
        return stadiumRepository.save(player);
    }
    public void deleteStadium(long id) {
        stadiumRepository.deleteById(id);
    }
    public void deleteAllStadiums() {
        stadiumRepository.deleteAll();
    }
}