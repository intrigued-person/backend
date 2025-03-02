package com.headout.dao;

import java.util.List;

import com.headout.dto.QuestionDto;
import com.headout.model.CityProfile;

public interface CityProfileDao {

	public String save(CityProfile cityProfile);

	public boolean checkAnswer(String city, String clue);

	public QuestionDto generateQuestion();

	public List<String> getFunFact(long id);

}
