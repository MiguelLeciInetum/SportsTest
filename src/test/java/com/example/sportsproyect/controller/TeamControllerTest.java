package com.example.sportsproyect.controller;

import com.example.sportsproyect.model.Stadium;
import com.example.sportsproyect.model.Team;
import com.example.sportsproyect.model.TeamDto;
import com.example.sportsproyect.service.StadiumService;
import com.example.sportsproyect.service.TeamService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TeamController.class)
class TeamControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TeamService teamService;
    private Team team;
    private List<Team> teamsList;
    private List<TeamDto> teamsDtoList;

    @BeforeEach
    void setUp() {
        team = new Team("Barcelona", "BCF");
        this.teamsList = new ArrayList<>();
        this.teamsList.add(new Team("Barcelona", "BCF"));
        this.teamsList.add(new Team("Zaragoza", "ZGZ"));
        this.teamsList.add(new Team("Madrid", "MDR"));
        this.teamsDtoList = new ArrayList<>();
        this.teamsDtoList.add(new TeamDto("Barcelona", "BCF"));
        this.teamsDtoList.add(new TeamDto("Zaragoza", "ZGZ"));
        this.teamsDtoList.add(new TeamDto("Madrid", "MDR"));
    }
    @Test
    public void getAllTeams() throws Exception {
        given(teamService.getAllTeams()).willReturn(teamsDtoList);
        this.mockMvc.perform(get("/teams"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(teamsList.size())));
    }
    @Test
    public void getTeamById() throws Exception {
        given(teamService.getTeamById(1L)).willReturn(team);
        this.mockMvc.perform(get("/teams/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(team.getName())))
                .andExpect(jsonPath("$.abbreviation", is(team.getAbbreviation())));
    }
    @Test
    public void createTeam() throws Exception {
        given(teamService.createTeam(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));
        ResultActions response = mockMvc.perform(post("/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(team)));
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void updateTeam() throws Exception {
        given(teamService.updateTeam(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));
        ResultActions response = mockMvc.perform(put("/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(team)));
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void deleteTeam() throws Exception {
        mockMvc.perform(delete("/teams/1")
                        .content(objectMapper.writeValueAsString(team))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void deleteAllTeams() throws Exception {
        mockMvc.perform(delete("/teams"))
                .andExpect(status().isOk());
    }
}
