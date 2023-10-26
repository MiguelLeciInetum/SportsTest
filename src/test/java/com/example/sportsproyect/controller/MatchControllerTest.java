package com.example.sportsproyect.controller;

import com.example.sportsproyect.model.Match;
import com.example.sportsproyect.model.Team;
import com.example.sportsproyect.service.MatchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(controllers = MatchController.class)
class MatchControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MatchService matchService;
    private List<Match> matchesList;
    private Match match;
    private List<Team> teamsList;

    @BeforeEach
    void setUp() {
        this.teamsList = new ArrayList<>();
        this.teamsList.add(new Team( "Real Zaragoza","ZGZ"));
        this.teamsList.add(new Team( "Real Madrid","MAD"));
        this.teamsList.add(new Team( "Barcelona","BCF"));
        this.matchesList = new ArrayList<>();
        this.matchesList.add(new Match( "1", new Date(), teamsList.get(0),teamsList.get(1),"1-2"));
        this.matchesList.add(new Match( "1", new Date(), teamsList.get(1),teamsList.get(2),"2-3"));
        this.matchesList.add(new Match( "1", new Date(), teamsList.get(2),teamsList.get(0),"3-1"));
        this.matchesList.add(new Match( "1", new Date(), teamsList.get(2),teamsList.get(0),"3-1"));
        match = new Match( "2", new Date(), teamsList.get(2),teamsList.get(0),"3-1");
    }
    @Test
    public void getAllMatchs() throws Exception{
        given(matchService.getAllMatchs()).willReturn(matchesList);
        this.mockMvc.perform(get("/matchs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(matchesList.size())));
    }
    @Test
    public void getMatchById() throws Exception{
        given(matchService.getMatchById(1L)).willReturn(match);
        this.mockMvc.perform(get("/matchs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.round", is(match.getRound())))
                .andExpect(jsonPath("$.score", is(match.getScore())));
    }
    @Test
    public void createMatch() throws Exception{
        given(matchService.createMatch(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));
        ResultActions response = mockMvc.perform(post("/matchs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(match)));
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void updateMatch() throws Exception{
        given(matchService.updateMatch(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));
        ResultActions response = mockMvc.perform(put("/matchs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(match)));
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void deleteMatch() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .delete("/matchs/1")
                        .content(objectMapper.writeValueAsString(match))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void deleteAllMatchs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/matchs"))
                .andExpect(status().isOk());
    }


        /*
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
