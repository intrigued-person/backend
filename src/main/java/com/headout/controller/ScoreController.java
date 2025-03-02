package com.headout.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.headout.service.ScoreService;

@RestController
@RequestMapping("/score")
@CrossOrigin("*")
public class ScoreController {

	private ScoreService service;

	public ScoreController(ScoreService service) {
		super();
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Long> getScore(@RequestParam boolean answer, @RequestParam long userId) {
		Long response = service.updateScore(answer, userId);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/get-score")
	public ResponseEntity<Integer> scoreId(@RequestParam long userId) {
		int score = service.getScore(userId);
		return ResponseEntity.ok(score);

	}

}
