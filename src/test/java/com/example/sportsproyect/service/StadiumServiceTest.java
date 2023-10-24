package com.example.sportsproyect.service;

import com.example.sportsproyect.model.Stadium;
import com.example.sportsproyect.repository.StadiumRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class StadiumServiceTest {
    @Mock
    private StadiumRepository stadiumRepository;
    private AutoCloseable autoCloseable;
    private StadiumService underTest;
    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new StadiumService(stadiumRepository);
    }
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }
    @Test
    void canGetAllStadiums() {
        //when
        underTest.getAllStadiums();
        //then
        verify(stadiumRepository).findAll();
    }
    @Test
    void getStadiumById() {
        Stadium mockedStadium = mock(Stadium.class);
        when(stadiumRepository.findById(1L)).thenReturn(Optional.of(mockedStadium));
        Stadium playerById = underTest.getStadiumById(1L);
        assertThat(playerById).isEqualTo(mockedStadium);
    }
    @Test
    void createStadium() {
        //when
        Stadium mockedStadium = mock(Stadium.class);
        underTest.createStadium(mockedStadium);
        //then
        verify(stadiumRepository).save(mockedStadium);
    }
    @Test
    void updateStadium() {
        //when
        Stadium mockedStadium = mock(Stadium.class);
        underTest.updateStadium(mockedStadium);
        //then
        verify(stadiumRepository).save(mockedStadium);
    }
    @Test
    @Disabled
    void deleteStadium() throws Exception {
        //when
        Stadium mockedStadium = mock(Stadium.class);
        underTest.createStadium(mockedStadium);
        underTest.deleteStadium(mockedStadium.getId());
        //then
        verify(stadiumRepository).delete(mockedStadium);
    }
    @Test
    void deleteAllStadiums() {
        //when
        underTest.deleteAllStadiums();
        //then
        verify(stadiumRepository).deleteAll();
    }
}