package com.headout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.headout.dao.CityProfileDao;
import com.headout.dto.CityProfileDto;
import com.headout.dto.QuestionDto;
import com.headout.model.CityProfile;
import com.headout.serviceImp.CityProfileServiceImp;

public class CityProfileServiceImplTest {

    @InjectMocks
    private CityProfileServiceImp service;

    @Mock
    private CityProfileDao dao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCityInfo_NullProfile() {
        String response = service.addCityInfo(null);
        assertEquals("City information is empty", response);
    }

    @Test
    public void testAddCityInfo_EmptyCity() {
        CityProfileDto dto = new CityProfileDto();
        dto.setCountry("USA");
        dto.setClues(Arrays.asList("Clue1"));
        dto.setFunFact(Arrays.asList("Fun Fact"));
        dto.setTrivia(Arrays.asList("Trivia"));

        String response = service.addCityInfo(dto);
        assertEquals("City is Empty", response);
    }

    @Test
    public void testAddCityInfo_EmptyCountry() {
        CityProfileDto dto = new CityProfileDto();
        dto.setCity("New York");
        dto.setClues(Arrays.asList("Clue1"));
        dto.setFunFact(Arrays.asList("Fun Fact"));
        dto.setTrivia(Arrays.asList("Trivia"));

        String response = service.addCityInfo(dto);
        assertEquals("Country is empty", response);
    }
    
   

    @Test
    public void testAddCityInfo_ValidProfile() {
        CityProfileDto dto = new CityProfileDto();
        dto.setCity("New York");
        dto.setCountry("USA");
        dto.setClues(Arrays.asList("Clue1", "Clue2"));
        dto.setFunFact(Arrays.asList("A fun fact about New York"));
        dto.setTrivia(Arrays.asList("Some trivia"));

        String response = service.addCityInfo(dto);
        assertEquals("City information added successfully", response);
        
        verify(dao, times(1)).save(any(CityProfile.class));
    }

    @Test
    public void testValidateAnswer_EmptyCity() {
        boolean result = service.validateAnswer("", "Clue1");
        assertFalse(result);
    }

    @Test
    public void testValidateAnswer_ValidAnswer() {
        when(dao.checkAnswer("New York", "Clue1")).thenReturn(true);
        
        boolean result = service.validateAnswer("New York", "Clue1");
        assertTrue(result);
    }

    @Test
    public void testValidateAnswer_InvalidAnswer() {
        when(dao.checkAnswer("New York", "Clue1")).thenReturn(false);
        
        boolean result = service.validateAnswer("New York", "Clue1");
        assertFalse(result);
    }

    @Test
    public void testGenerateQuestion() {
        QuestionDto mockQuestionDto = new QuestionDto();
        mockQuestionDto.setClues(Arrays.asList("Clue1", "Clue2"));
        mockQuestionDto.setCities(Arrays.asList("City1", "City2", "New York"));
        
        when(dao.generateQuestion()).thenReturn(mockQuestionDto);
        
        QuestionDto result = service.generateQuestion();
        assertNotNull(result);
        assertEquals(mockQuestionDto.getClues(), result.getClues());
        assertEquals(mockQuestionDto.getCities(), result.getCities());
        verify(dao, times(1)).generateQuestion();
    }

}