package com.headout.dto;

public class ChallangeDto {

	private long challangerId;

	private long myUserId;

	private int myScore;

	public ChallangeDto() {
		super();
	}

	public long getChallangerId() {
		return challangerId;
	}

	public void setChallangerId(long challangerId) {
		this.challangerId = challangerId;
	}

	public long getMyUserId() {
		return myUserId;
	}

	public void setMyUserId(long myUserId) {
		this.myUserId = myUserId;
	}

	public int getMyScore() {
		return myScore;
	}

	public void setMyScore(int myScore) {
		this.myScore = myScore;
	}

}
