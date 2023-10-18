package com.example.sportsproyect.service;

import com.example.sportsproyect.model.Match;
import com.example.sportsproyect.model.Player;
import com.example.sportsproyect.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    @Autowired
    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository)
    {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(long id) {
        return playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Match not found"));
    }
    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(Player player) {
        return playerRepository.save(player);
    }
    public void deletePlayer(long id) {
        playerRepository.deleteById(id);
    }

    public void deleteAllPlayers() {
        playerRepository.deleteAll();
    }

}