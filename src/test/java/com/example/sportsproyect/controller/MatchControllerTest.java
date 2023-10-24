package com.example.sportsproyect.controller;

import com.example.sportsproyect.model.Match;
import com.example.sportsproyect.model.Team;
import com.example.sportsproyect.service.MatchService;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(controllers = MatchController.class)
class MatchControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MatchService matchService;
    private List<Match> matchesList;
    private List<Team> teamsList;

    @BeforeEach
    void setUp() {
        this.teamsList = new ArrayList<>();
        this.teamsList.add(new Team( "Real Zaragoza","ZGZ"));
        this.teamsList.add(new Team( "Real Madrid","MAD"));
        this.teamsList.add(new Team( "Barcelona","BCF"));
        this.matchesList = new ArrayList<>();
        this.matchesList.add(new Match( "1", new Date(), teamsList.get(1),teamsList.get(2),"1-2"));
        this.matchesList.add(new Match( "1", new Date(), teamsList.get(2),teamsList.get(3),"2-3"));
        this.matchesList.add(new Match( "1", new Date(), teamsList.get(3),teamsList.get(1),"3-1"));
    }
    @Test
    public void getAllMatchs() throws Exception{
        given(matchService.getAllMatchs()).willReturn(matchesList);
        this.mockMvc.perform(get(""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(matchesList.size())));
    }
   /* @Test
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
