package com.headout.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;

	@Column
	private String firstName;

	@Column
	private String email;

	@Column(unique = true)
	private String userName;

	@Column
	private String password;

	@Column(nullable = true)
	private Long challangerId;

	@Column(nullable = true)
	private Integer challengerScore;

	public User() {
		super();
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getChallangerId() {
		return challangerId;
	}

	public void setChallangerId(Long challangerId) {
		this.challangerId = challangerId;
	}

	public Integer getChallengerScore() {
		return challengerScore;
	}

	public void setChallengerScore(Integer challengerScore) {
		this.challengerScore = challengerScore;
	}

}
