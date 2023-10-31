package com.example.sportsproyect.service;

import com.example.sportsproyect.mappers.TeamMapper;
import com.example.sportsproyect.model.Team;
import com.example.sportsproyect.repository.TeamRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class TeamServiceTest {
    @Mock
    private TeamRepository teamsRepository;
    @Mock
    private TeamMapper teamMapper;
    private AutoCloseable autoCloseable;
    private TeamService underTest;
    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new TeamService(teamsRepository,teamMapper);
    }
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }
    @Test
    void canGetAllTeams() {
        //when
        underTest.getAllTeams();
        //then
        verify(teamsRepository).findAll();
    }
    @Test
    void getTeamById() {
        Team mockedTeam = mock(Team.class);
        when(teamsRepository.findById(1L)).thenReturn(Optional.of(mockedTeam));
        Team playerById = underTest.getTeamById(1L);
        assertThat(playerById).isEqualTo(mockedTeam);
    }
    @Test
    void createTeam() {
        //when
        Team mockedTeam = mock(Team.class);
        underTest.createTeam(mockedTeam);
        //then
        verify(teamsRepository).save(mockedTeam);
    }
    @Test
    void updateTeam() {
        //when
        Team mockedTeam = mock(Team.class);
        underTest.updateTeam(mockedTeam);
        //then
        verify(teamsRepository).save(mockedTeam);
    }
    @Test
    @Disabled
    void deleteTeam() throws Exception {
        //when
        Team mockedTeam = mock(Team.class);
        underTest.createTeam(mockedTeam);
        underTest.deleteTeam(mockedTeam.getId());
        //then
        verify(teamsRepository).delete(mockedTeam);
    }
    @Test
    void deleteAllTeams() {
        //when
        underTest.deleteAllTeams();
        //then
        verify(teamsRepository).deleteAll();
    }
}