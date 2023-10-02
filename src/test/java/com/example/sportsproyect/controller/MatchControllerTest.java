package com.example.sportsproyect.controller;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class MatchControllerTest {

    @Autowired
    private TeamController teamController;
    @Autowired
    private MatchController controller;

   /* @Test
    public void getAllMatchs() {
        List<Match> matchs = controller.getAllMatchs();
        Assertions.assertNotNull(matchs);
    }
    @Test
    public void getMatchById() {
        ResponseEntity<List<Match>> responseGet = controller.getAllMatchs();
        Match match = responseGet.getBody().stream().findFirst().get();
        ResponseEntity<Match> response = controller.getMatchById(match.getId());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    public void createMatch() {
        List<Team> teams = teamController.getAllteams().getBody();
        ResponseEntity response = controller.createMatch(new Match("1", new Date(), teams.get(1), teams.get(2),"11-11"));
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    public void updateMatch() {
        ResponseEntity<List<Match>> responseGet = controller.getAllMatchs();
        Match match = responseGet.getBody().stream().findFirst().get();
        match.setScore("10-10");
        ResponseEntity<Match> response = controller.updateMatch(match.getId(), match);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    public void deleteMatch() {
        ResponseEntity response = controller.deleteMatch(1);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        ResponseEntity<Match> responseDel = controller.getMatchById(1);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNull(responseDel.getBody());
        controller.play();
    }
    @Test
    public void deleteAllMatchs() {
        ResponseEntity response = controller.deleteAllMatchs();
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        ResponseEntity<List<Match>> responseDel = controller.getAllMatchs();
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseDel.getStatusCode());
        Assertions.assertNull(responseDel.getBody());
        controller.play();
    }
    @Test
    public void play() {
        ResponseEntity response = controller.play();
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
    @Test
    public void getLeaderBoard() {
        ResponseEntity<List<LeaderBoardDto>> response = controller.getLeaderBoard();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }*/

}
