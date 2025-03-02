package com.headout.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.headout.dto.ChallangeDto;
import com.headout.dto.PendingDto;
import com.headout.dto.UserDto;
import com.headout.model.User;
import com.headout.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

	private UserService service;

	public UserController(UserService service) {
		super();
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User user) {
		String response = service.createUser(user);

		if (response != null) {
			return ResponseEntity.ok(user);
		}

		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/login")
	public ResponseEntity<UserDto> login(@RequestParam String email, @RequestParam String password) {
		UserDto response = service.checkLogin(email, password);
		if (response != null) {
			return ResponseEntity.ok(response);
		}

		return ResponseEntity.badRequest().build();

	}

	@PostMapping("/challange")
	public ResponseEntity<String> challangeFriend(@RequestBody ChallangeDto challangeDto) {
		String response = service.giveChallange(challangeDto);
		if (response != null) {
			return ResponseEntity.ok(response);
		}

		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/all")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> dto = service.getAll();
		if (!dto.isEmpty()) {
			return ResponseEntity.ok(dto);
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/pending")
	public ResponseEntity<PendingDto> getPending(@RequestParam long userId) {

		PendingDto score = service.getPending(userId);
		if (score != null) {
			return ResponseEntity.ok(score);
		}

		return ResponseEntity.badRequest().build();

	}

}
