package com.headout.dto;

import java.util.List;

public class QuestionDto {

	private long id;

	private List<String> clues;

	private List<String> cities;

	public QuestionDto() {
		super();
	}

	public List<String> getClues() {
		return clues;
	}

	public void setClues(List<String> clues) {
		this.clues = clues;
	}

	public List<String> getCities() {
		return cities;
	}

	public void setCities(List<String> cities) {
		this.cities = cities;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
