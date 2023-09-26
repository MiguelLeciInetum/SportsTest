package com.example.sportsproyect.controller;

import com.example.sportsproyect.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest
class PlayerControllerTest {

    @Autowired
    private PlayerController controller;
    @Autowired
    private TeamController teamController;

    @Test
    public void getAllPlayers() {
        ResponseEntity<List<Player>> response = controller.getAllPlayers();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    public void getPlayerById() {
        ResponseEntity<List<Player>> responseGet = controller.getAllPlayers();
        Player player = responseGet.getBody().stream().findFirst().get();
        ResponseEntity<Player> response = controller.getPlayerById(player.getId());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    public void createPlayer() {
        controller.deleteAllPlayers();
        List<Team> teams = teamController.getAllteams().getBody();
        ResponseEntity response = controller.createPlayer(new Player("Steven","Meurrens","BEG",teams.get(1)));
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    public void updateMatch() {
        ResponseEntity<List<Player>> responseGet = controller.getAllPlayers();
        Player player = responseGet.getBody().stream().findFirst().get();
        player.setNationality("ESP");
        ResponseEntity<Player> response = controller.updatePlayer(player.getId(), player);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    public void deletePlayer() {
        ResponseEntity response = controller.deletePlayer(1);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        ResponseEntity<Player> responseDel = controller.getPlayerById(1);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNull(responseDel.getBody());
        List<Team> teams = teamController.getAllteams().getBody();
        controller.createPlayer(new Player("Steven","Meurrens","BEG",teams.get(1)));
    }
    @Test
    public void deleteAllPlayers() {
        ResponseEntity response = controller.deleteAllPlayers();
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        ResponseEntity<List<Player>> responseDel = controller.getAllPlayers();
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseDel.getStatusCode());
        Assertions.assertNull(responseDel.getBody());
        List<Team> teams = teamController.getAllteams().getBody();
        controller.createPlayer(new Player("Steven","Meurrens","BEG",teams.get(1)));
    }
}
