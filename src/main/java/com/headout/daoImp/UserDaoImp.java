package com.headout.daoImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.headout.dao.UserDao;
import com.headout.dto.ChallangeDto;
import com.headout.dto.PendingDto;
import com.headout.dto.UserDto;
import com.headout.model.Score;
import com.headout.model.User;

import jakarta.persistence.EntityManager;

@Repository
public class UserDaoImp implements UserDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public String createUser(User user) {
		if (user.getUserName() != null) {
			Score score = new Score();
			score.setScores(0);
			score.setUser(user);
			entityManager.persist(score);
			entityManager.persist(user);
			return "User Added Successfully";
		}
		return "Failed to add user";
	}

	@Override
	public UserDto login(String email, String password) {
		String hql = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password";
		User user = (User) entityManager.createQuery(hql).setParameter("email", email)
				.setParameter("password", password).getSingleResult();

		if (user != null) {
			UserDto dto = new UserDto();
			dto.setUserId(user.getUserId());
			dto.setUserName(user.getUserName());
			return dto;
		}

		return new UserDto();

	}

	@Override
	public String giveChallange(ChallangeDto challangeDto) {
		long checkId = challangeDto.getChallangerId();
		User user = entityManager.find(User.class, checkId);
		if (user != null) {
			user.setChallangerId(challangeDto.getMyUserId());
			user.setChallengerScore(challangeDto.getMyScore());
			entityManager.merge(user);
			return "Challanger sent";

		}

		return "Failed to challange";
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = entityManager.createQuery("FROM User u", User.class).getResultList();

		List<UserDto> dtoList = new ArrayList<>();
		for (User user : users) {
			UserDto userDto = new UserDto();
			userDto.setUserId(user.getUserId());
			userDto.setUserName(user.getUserName());
			dtoList.add(userDto);
		}
		return dtoList;
	}

	@Override
	public PendingDto getPending(long userId) {
		User user = entityManager.find(User.class, userId);
		PendingDto dto = new PendingDto();
		dto.setScore(user.getChallengerScore());

		User us = entityManager.find(User.class, user.getChallangerId());

		dto.setUserName(us.getUserName());

		return dto;
	}

}
