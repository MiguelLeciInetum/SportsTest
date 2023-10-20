package com.example.sportsproyect.service;

import com.example.sportsproyect.model.Match;
import com.example.sportsproyect.model.Team;
import com.example.sportsproyect.repository.MatchRepository;
import com.example.sportsproyect.repository.TeamRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class MatchServiceTest {
    @Mock
    private MatchRepository matchRepository;
    @Mock
    private TeamRepository teamRepository;
    private AutoCloseable autoCloseable;
    private MatchService underTest;
    @BeforeEach
    void setUp() {
        autoCloseable =MockitoAnnotations.openMocks(this);
        underTest = new MatchService(matchRepository,teamRepository);
    }
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }
    @Test
    void canGetAllMatchs() {
        //when
        underTest.getAllMatchs();
        //then
        verify(matchRepository).findAll();
    }
    @Test
    void getMatchById() {
        Match mockedMatch = mock(Match.class);
        when(matchRepository.findById(1L)).thenReturn(Optional.of(mockedMatch));
        Match matchById = underTest.getMatchById(1L);
        assertThat(matchById).isEqualTo(mockedMatch);
    }
    @Test
    void createMatch() {
        //when
        Match mockedMatch = mock(Match.class);
        underTest.createMatch(mockedMatch);
        //then
        verify(matchRepository).save(mockedMatch);
    }
    @Test
    void updateMatch() {
        //when
        Match mockedMatch = mock(Match.class);
        underTest.updateMatch(mockedMatch);
        //then
        verify(matchRepository).save(mockedMatch);
    }
    @Test
    @Disabled
    void deleteMatch() throws Exception {
        //when
        Match mockedMatch = mock(Match.class);
        underTest.createMatch(mockedMatch);
        underTest.deleteMatch(mockedMatch.getId());
        //then
        verify(matchRepository).delete(mockedMatch);
    }
    @Test
    void deleteAllMatchs() {
        //when
        underTest.deleteAllMatchs();
        //then
        verify(matchRepository).deleteAll();
    }
    @Test
    void getLeaderBoard() {
        //when
        underTest.getLeaderBoard();
        //then
        verify(matchRepository).findAll();
        verify(teamRepository).findAll();
    }
}