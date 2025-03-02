package com.headout.dao;

public interface ScoreDao {

	public long updateScore(boolean answer, long userId);

	public int getScore(long userId);

//	public Long getScore(long userId);

}
