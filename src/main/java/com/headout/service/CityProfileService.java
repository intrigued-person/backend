package com.headout.service;

import java.util.List;

import com.headout.dto.CityProfileDto;
import com.headout.dto.QuestionDto;

public interface CityProfileService {

	public String addCityInfo(CityProfileDto profileDto);

	public boolean validateAnswer(String city, String clue);

	public QuestionDto generateQuestion();

	public List<String> getFunFacts(long id);
}
