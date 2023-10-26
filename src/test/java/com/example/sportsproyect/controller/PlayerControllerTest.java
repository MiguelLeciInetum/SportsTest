package com.example.sportsproyect.controller;

import com.example.sportsproyect.model.*;
import com.example.sportsproyect.service.MatchService;
import com.example.sportsproyect.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PlayerController.class)
class PlayerControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlayerService playerService;
    private Team team;
    private List<Player> playersList;
    private Player player;

    @BeforeEach
    void setUp() {
        team = new Team( "Barcelona","BCF");
        this.playersList = new ArrayList<>();
        this.playersList.add(new Player("Jose","Cruz","SPN",team));
        this.playersList.add(new Player("Paco","Paquito","SPN",team));
        this.playersList.add(new Player("Napoleon","Aparte","SPN",team));
        player = new Player("Napoleon","Aparte","SPN",team);
    }
    @Test
    public void getAllPlayers() throws Exception{
        given(playerService.getAllPlayers()).willReturn(playersList);
        this.mockMvc.perform(get("/players"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(playersList.size())));
    }
    @Test
    public void getPlayerById() throws Exception{
        given(playerService.getPlayerById(1L)).willReturn(player);
        this.mockMvc.perform(get("/players/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(player.getName())))
                .andExpect(jsonPath("$.surname", is(player.getSurname())))
                .andExpect(jsonPath("$.nationality", is(player.getNationality())));
    }
    @Test
    public void createPlayer() throws Exception{
        given(playerService.createPlayer(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));
        ResultActions response = mockMvc.perform(post("/players")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(player)));
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void updatePlayer() throws Exception{
        given(playerService.updatePlayer(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));
        ResultActions response = mockMvc.perform(put("/players")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(player)));
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void deletePlayer() throws Exception {
        mockMvc.perform(delete("/players/1")
                        .content(objectMapper.writeValueAsString(player))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void deleteAllPlayers() throws Exception {
        mockMvc.perform(delete("/players"))
                .andExpect(status().isOk());
    }
}