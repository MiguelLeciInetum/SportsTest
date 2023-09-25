package com.example.sportsproyect.controller;

import com.example.sportsproyect.model.LeaderBoardDto;
import com.example.sportsproyect.model.Match;
import com.example.sportsproyect.model.Stadium;
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
class StadiumControllerTest {

    @Autowired
    private StadiumController controller;
    @Autowired
    private TeamController teamController;

    @Test
    public void getAllStadiums() {
        ResponseEntity<List<Stadium>> response = controller.getAllStadiums();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    public void getStadiumById() {
        ResponseEntity<List<Stadium>> responseGet = controller.getAllStadiums();
        Stadium stadium = responseGet.getBody().stream().findFirst().get();
        ResponseEntity<Stadium> response = controller.getStadiumById(stadium.getId());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    public void createStadium() {
        controller.deleteAllStadiums();
        List<Team> teams = teamController.getAllteams().getBody();
        ResponseEntity response = controller.createStadium(new Stadium("Alcorica Facebook", "Chiclana", teams.stream().findFirst().get()));
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    public void updateMatch() {
        ResponseEntity<List<Stadium>> responseGet = controller.getAllStadiums();
        Stadium stadium = responseGet.getBody().stream().findFirst().get();
        stadium.setName("PradillaDebro");
        ResponseEntity<Stadium> response = controller.updateStadium(stadium.getId(), stadium);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    public void deleteStadium() {
        ResponseEntity response = controller.deleteStadium(1);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        ResponseEntity<Stadium> responseDel = controller.getStadiumById(1);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNull(responseDel.getBody());
        List<Team> teams = teamController.getAllteams().getBody();
        controller.createStadium(new Stadium("Alcorica Facebook", "Chiclana", teams.stream().findFirst().get()));
    }
    @Test
    public void deleteAllStadiums() {
        ResponseEntity response = controller.deleteAllStadiums();
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        ResponseEntity<List<Stadium>> responseDel = controller.getAllStadiums();
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseDel.getStatusCode());
        Assertions.assertNull(responseDel.getBody());
        List<Team> teams = teamController.getAllteams().getBody();
        controller.createStadium(new Stadium("Alcorica Facebook", "Chiclana", teams.stream().findFirst().get()));
    }
}
