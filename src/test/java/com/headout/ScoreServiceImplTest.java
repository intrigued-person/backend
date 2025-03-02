package com.headout;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.headout.dao.ScoreDao;
import com.headout.model.Score;
import com.headout.model.User;
import com.headout.serviceImp.ScoreServiceImp;

public class ScoreServiceImplTest {

	@InjectMocks
	private ScoreServiceImp scoreService;

	@Mock
	private ScoreDao scoreDao;

	@Mock
	private User user;

	@Mock
	private Score score;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testUpdateScore_AnswerCorrect_UserExists() {
		long userId = 1L;
		boolean answer = true;

		// Mock the behavior of the DAO
		when(scoreDao.updateScore(answer, userId)).thenReturn(10L);

		long updatedScore = scoreService.updateScore(answer, userId);

		assertEquals(10L, updatedScore);
		verify(scoreDao, times(1)).updateScore(answer, userId);
	}

	@Test
	public void testUpdateScore_AnswerIncorrect_UserExists() {
		long userId = 1L;
		boolean answer = false;

		// Mock the behavior of the DAO
		when(scoreDao.updateScore(answer, userId)).thenReturn(0L);

		long updatedScore = scoreService.updateScore(answer, userId);

		assertEquals(0L, updatedScore);
		verify(scoreDao, times(1)).updateScore(answer, userId);
	}

}