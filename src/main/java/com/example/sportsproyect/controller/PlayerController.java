package com.example.sportsproyect.controller;

import com.example.sportsproyect.model.Player;
import com.example.sportsproyect.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    @GetMapping()
    public ResponseEntity<List<Player>> getAllPlayers(@RequestParam(required = false) String title) {
        try {
            List<Player> players = new ArrayList<Player>();

            if (title == null)
                playerRepository.findAll().forEach(players::add);
            if (players.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(players, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable("id") long id) {
        Optional<Player> playerData = playerRepository.findById(id);

        if (playerData.isPresent()) {
            return new ResponseEntity<>(playerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        try {
            Player _player = playerRepository
                    .save(new Player(player.getName(), player.getSurname(), player.getNationality(), player.getTeam()));
            return new ResponseEntity<>(_player, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable("id") long id, @RequestBody Player player) {
        Optional<Player> playerData = playerRepository.findById(id);

        if (playerData.isPresent()) {
            Player _player = playerData.get();
            _player.setName(player.getName());
            _player.setSurname(player.getSurname());
            _player.setNationality(player.getNationality());
            _player.setTeam(player.getTeam());
            return new ResponseEntity<>(playerRepository.save(_player), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePlayer(@PathVariable("id") long id) {
        try {
            playerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAllPlayers() {
        try {
            playerRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}