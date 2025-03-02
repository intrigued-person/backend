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

import com.headout.dto.CityProfileDto;
import com.headout.dto.QuestionDto;
import com.headout.service.CityProfileService;

@RestController
@CrossOrigin("*")
@RequestMapping("/city")
public class CityProfileController {

	private CityProfileService service;

	public CityProfileController(CityProfileService service) {
		super();
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<CityProfileDto> addCityInfo(@RequestBody CityProfileDto citydto) {
		String response = service.addCityInfo(citydto);
		if (response != null) {
			return ResponseEntity.ok(citydto);
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/check-answer")
	public ResponseEntity<Boolean> checkAnswer(@RequestParam String city, @RequestParam String clue) {
		boolean response = service.validateAnswer(city, clue);
		if (response) {
			return ResponseEntity.ok(response);
		}

		return ResponseEntity.badRequest().build();

	}

	@GetMapping("/generate-question")
	public ResponseEntity<QuestionDto> generate() {
		QuestionDto dto = service.generateQuestion();
		if (dto != null) {
			return ResponseEntity.ok(dto);
		}

		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/fun-fact")
	public ResponseEntity<List<String>> getFunFact(@RequestParam long id) {
		List<String> response = service.getFunFacts(id);
		if (!response.isEmpty()) {
			return ResponseEntity.ok(response);
		}

		return ResponseEntity.badRequest().build();

	}
}
