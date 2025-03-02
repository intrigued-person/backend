package com.headout.daoImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.headout.dao.ScoreDao;
import com.headout.model.Score;
import com.headout.model.User;

import jakarta.persistence.EntityManager;

@Repository
public class ScoreDaoImp implements ScoreDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public long updateScore(boolean answer, long userId) {
		User user = entityManager.find(User.class, userId);
//		Score score = new Score();
		String hql = "SELECT s FROM Score s WHERE s.user.userId = :userId";
		Score score = (Score) entityManager.createQuery(hql).setParameter("userId", userId).getSingleResult();
		if (user != null) {
			if (answer) {
				int s = score.getScores();
				score.setScores(s + 10);
				entityManager.merge(score);
				return score.getScores();

			}
		}
		return 0;
	}

	@Override
	public int getScore(long userId) {
		String hql = "SELECT s.scores FROM Score s WHERE s.user.userId = :userId";
		int score = entityManager.createQuery(hql, Integer.class).setParameter("userId", userId).getSingleResult();
		return score;
	}

}
