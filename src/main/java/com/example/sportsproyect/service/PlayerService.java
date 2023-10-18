package com.example.sportsproyect.service;

import com.example.sportsproyect.model.Player;
import com.example.sportsproyect.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    @Autowired
    public PlayerService(PlayerRepository playerRepository)
    {
        this.playerRepository = playerRepository;
    }
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
    public Player getPlayerById(long id) {
        return playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Player not found"));
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