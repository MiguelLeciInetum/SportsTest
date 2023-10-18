package com.example.sportsproyect.controller;

import com.example.sportsproyect.model.Player;
import com.example.sportsproyect.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("players")
public class PlayerController {
    @Autowired
    PlayerService playerService;
    @GetMapping()
    public List<Player> getAllPlayers()  {
        return playerService.getAllPlayers();
    }
    @GetMapping("{id}")
    public Player getPlayerById(@PathVariable("id") long id) {
        return playerService.getPlayerById(id);
    }
    @PostMapping()
    public Player createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }
    @PutMapping()
    public Player updatePlayer(@RequestBody Player player) {
        return playerService.updatePlayer(player);
    }
    @DeleteMapping("{id}")
    public void deletePlayer(@PathVariable("id") long id) {
        playerService.deletePlayer(id);
    }
    @DeleteMapping()
    public void deleteAllPlayers() {
        playerService.deleteAllPlayers();
    }
}