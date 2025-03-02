package com.headout.service;

import java.util.List;

import com.headout.dto.ChallangeDto;
import com.headout.dto.PendingDto;
import com.headout.dto.UserDto;
import com.headout.model.User;

public interface UserService {

	public String createUser(User user);

	public UserDto checkLogin(String email, String password);

	public String giveChallange(ChallangeDto challangeDto);

	public List<UserDto> getAll();
	
	public PendingDto getPending(long userId);

}
