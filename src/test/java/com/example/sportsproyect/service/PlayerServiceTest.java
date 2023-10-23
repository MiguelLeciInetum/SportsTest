package com.example.sportsproyect.service;

import com.example.sportsproyect.model.Player;
import com.example.sportsproyect.repository.*;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class PlayerServiceTest {
    @Mock
    private PlayerRepository playerRepository;
    private AutoCloseable autoCloseable;
    private PlayerService underTest;
    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new PlayerService(playerRepository);
    }
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }
    @Test
    void canGetAllPlayers() {
        //when
        underTest.getAllPlayers();
        //then
        verify(playerRepository).findAll();
    }
    @Test
    void getPlayerById() {
        Player mockedPlayer = mock(Player.class);
        when(playerRepository.findById(1L)).thenReturn(Optional.of(mockedPlayer));
        Player playerById = underTest.getPlayerById(1L);
        assertThat(playerById).isEqualTo(mockedPlayer);
    }
    @Test
    void createPlayer() {
        //when
        Player mockedPlayer = mock(Player.class);
        underTest.createPlayer(mockedPlayer);
        //then
        verify(playerRepository).save(mockedPlayer);
    }
    @Test
    void updatePlayer() {
        //when
        Player mockedPlayer = mock(Player.class);
        underTest.updatePlayer(mockedPlayer);
        //then
        verify(playerRepository).save(mockedPlayer);
    }
    @Test
    @Disabled
    void deletePlayer() throws Exception {
        //when
        Player mockedPlayer = mock(Player.class);
        underTest.createPlayer(mockedPlayer);
        underTest.deletePlayer(mockedPlayer.getId());
        //then
        verify(playerRepository).delete(mockedPlayer);
    }
    @Test
    void deleteAllPlayers() {
        //when
        underTest.deleteAllPlayers();
        //then
        verify(playerRepository).deleteAll();
    }
}