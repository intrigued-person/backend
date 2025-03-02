package com.headout.dao;

import java.util.List;

import com.headout.dto.ChallangeDto;
import com.headout.dto.PendingDto;
import com.headout.dto.UserDto;
import com.headout.model.User;

public interface UserDao {

	public String createUser(User user);

	public UserDto login(String email, String password);

	public String giveChallange(ChallangeDto challangeDto);

	public List<UserDto> getAllUsers();

	public PendingDto getPending(long userId);
}
