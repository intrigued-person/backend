package com.headout.dto;

public class AnswerDto {

	private boolean isCorrect;

	private long questionId;

	public AnswerDto() {
		super();
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

}
