package com.headout.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.headout.dao.UserDao;
import com.headout.dto.ChallangeDto;
import com.headout.dto.PendingDto;
import com.headout.dto.UserDto;
import com.headout.model.User;
import com.headout.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImp implements UserService {

	@Autowired
	private UserDao dao;

	@Override
	public String createUser(User user) {
		if (user != null) {
			if (user.getEmail() != null && user.getFirstName() != null) {
				String userName = createUserName(user.getEmail(), user.getFirstName());
				user.setUserName(userName);
				dao.createUser(user);
				return "User Added";
			}
		}

		return "User not added";
	}

	public String createUserName(String email, String firstName) {
		String emailPrefix = email.split("@")[0];

		String trimmedFirstName = firstName.trim().toLowerCase();

		String userName = emailPrefix + "_" + trimmedFirstName;

		return userName;
	}

	@Override
	public UserDto checkLogin(String email, String password) {
		if (email != null && password != null) {
			return dao.login(email, password);
		}
		return null;

	}

	@Override
	public String giveChallange(ChallangeDto challangeDto) {
		if (challangeDto.getChallangerId() != 0 && challangeDto.getMyUserId() != 0) {
			return dao.giveChallange(challangeDto);
		}
		return "Failed to challange";
	}

	@Override
	public List<UserDto> getAll() {
		return dao.getAllUsers();
	}

	@Override
	public PendingDto getPending(long userId) {
		return dao.getPending(userId);
	}

}
