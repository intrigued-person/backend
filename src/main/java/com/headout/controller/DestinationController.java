package com.headout.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.headout.model.Destination;
import com.headout.service.DestinationService;

@RestController
@CrossOrigin("*")
@RequestMapping("/destination")
public class DestinationController {

	private DestinationService service;

	public DestinationController(DestinationService service) {
		super();
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Destination> saveDestination(@RequestBody Destination destination) {
		String response = service.addDestinations(destination);
		if (response != null) {
			return ResponseEntity.ok(destination);
		}

		return ResponseEntity.badRequest().build();
	}

}
