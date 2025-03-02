package com.headout.dto;

import java.util.List;

public class CityProfileDto {

	private String city;

	private String country;

	private List<String> clues;

	private List<String> funFact;

	private List<String> trivia;

	public CityProfileDto() {
		super();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<String> getClues() {
		return clues;
	}

	public void setClues(List<String> clues) {
		this.clues = clues;
	}

	public List<String> getFunFact() {
		return funFact;
	}

	public void setFunFact(List<String> funFact) {
		this.funFact = funFact;
	}

	public List<String> getTrivia() {
		return trivia;
	}

	public void setTrivia(List<String> trivia) {
		this.trivia = trivia;
	}

}
