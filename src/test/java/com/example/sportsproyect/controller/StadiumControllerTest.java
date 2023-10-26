package com.example.sportsproyect.controller;

import com.example.sportsproyect.model.Player;
import com.example.sportsproyect.model.Stadium;
import com.example.sportsproyect.model.Team;
import com.example.sportsproyect.service.PlayerService;
import com.example.sportsproyect.service.StadiumService;
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
import org.springframework.jdbc.core.metadata.HsqlTableMetaDataProvider;
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

@WebMvcTest(controllers = StadiumController.class)
class StadiumControllerTest {
        @Autowired
        private ObjectMapper objectMapper;
        @Autowired
        private MockMvc mockMvc;
        @MockBean
        private StadiumService stadiumService;
        private Team team;
        private List<Stadium> stadiumsList;
        private Stadium stadium;

        @BeforeEach
        void setUp() {
            team = new Team( "Barcelona","BCF");
            this.stadiumsList = new ArrayList<>();
            this.stadiumsList.add(new Stadium("Santiago","Madrid",team));
            this.stadiumsList.add(new Stadium("Romareda","Zaragoza",team));
            this.stadiumsList.add(new Stadium("Spotify","Barcelona",team));
            stadium = new Stadium("Santiago","Madrid",team);
        }
        @Test
        public void getAllStadiums() throws Exception{
            given(stadiumService.getAllStadiums()).willReturn(stadiumsList);
            this.mockMvc.perform(get("/stadiums"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()", is(stadiumsList.size())));
        }
        @Test
        public void getStadiumById() throws Exception{
            given(stadiumService.getStadiumById(1L)).willReturn(stadium);
            this.mockMvc.perform(get("/stadiums/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name", is(stadium.getName())))
                    .andExpect(jsonPath("$.location", is(stadium.getLocation())));
        }
        @Test
        public void createStadium() throws Exception{
            given(stadiumService.createStadium(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));
            ResultActions response = mockMvc.perform(post("/stadiums")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(stadium)));
            response.andExpect(MockMvcResultMatchers.status().isOk());
        }
        @Test
        public void updateStadium() throws Exception{
            given(stadiumService.updateStadium(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));
            ResultActions response = mockMvc.perform(put("/stadiums")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(stadium)));
            response.andExpect(MockMvcResultMatchers.status().isOk());
        }
        @Test
        public void deleteStadium() throws Exception {
            mockMvc.perform(delete("/stadiums/1")
                            .content(objectMapper.writeValueAsString(stadium))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
        @Test
        public void deleteAllStadiums() throws Exception {
            mockMvc.perform(delete("/stadiums"))
                    .andExpect(status().isOk());
        }
}
