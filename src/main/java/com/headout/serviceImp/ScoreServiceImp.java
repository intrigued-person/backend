package com.headout.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.headout.dao.ScoreDao;
import com.headout.service.ScoreService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ScoreServiceImp implements ScoreService {

	@Autowired
	private ScoreDao dao;

	@Override
	public long updateScore(boolean answer, long userId) {
		return dao.updateScore(answer, userId);
	}

	@Override
	public int getScore(long userId) {
		return dao.getScore(userId);
	}

}
