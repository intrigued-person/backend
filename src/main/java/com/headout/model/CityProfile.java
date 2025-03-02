package com.headout.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cityprofile")
public class CityProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String city;

	@Column
	private String country;

	@Column(columnDefinition = "TEXT")
	private List<String> clues;

	@Column(columnDefinition = "TEXT")
	private List<String> funFact;

	@Column(columnDefinition = "TEXT")
	private List<String> trivia;

	public CityProfile() {
		super();
	}

	public CityProfile(String city, String country, List<String> clues, List<String> funFact, List<String> trivia) {
		super();
		this.city = city;
		this.country = country;
		this.clues = clues;
		this.funFact = funFact;
		this.trivia = trivia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
