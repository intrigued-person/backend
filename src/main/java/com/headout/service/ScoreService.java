package com.headout.service;

public interface ScoreService {

	public long updateScore(boolean answer, long userId);

	public int getScore(long userId);

}
