package com.example.sportsproyect.controller;

import com.example.sportsproyect.model.LeaderBoardDto;
import com.example.sportsproyect.model.Match;
import com.example.sportsproyect.model.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

@SpringBootTest
class TeamControllerTest {

    @Autowired
    private TeamController controller;

    @Test
    public void getAllteams() {
        ResponseEntity<List<Team>> response = controller.getAllteams();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    public void getTeamById() {
        ResponseEntity<List<Team>> responseGet = controller.getAllteams();
        Team team = responseGet.getBody().stream().findFirst().get();
        ResponseEntity<Team> response = controller.getTeamById(team.getId());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    public void createTeam() {
        ResponseEntity response = controller.createTeam(new Team("Tauste FC","TAU"));
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    public void updateTeam() {
        ResponseEntity<List<Team>> responseGet = controller.getAllteams();
        Team team = responseGet.getBody().stream().findFirst().get();
        team.setAbbreviation("USA");
        ResponseEntity<Team> response = controller.updateTeam(team.getId(), team);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    public void deleteTeam() {
        ResponseEntity response = controller.deleteTeam(1);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        ResponseEntity<Team> responseDel = controller.getTeamById(1);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNull(responseDel.getBody());
        controller.createTeam(new Team("Tauste FC","TAU"));
    }
    @Test
    public void deleteAllTeams() {
        ResponseEntity response = controller.deleteAllTeams();
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        ResponseEntity<List<Team>> responseDel = controller.getAllteams();
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseDel.getStatusCode());
        Assertions.assertNull(responseDel.getBody());
        controller.createTeam(new Team("Tauste FC","TAU"));
    }
}