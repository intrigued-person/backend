package com.headout.serviceImp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.headout.dao.CityProfileDao;
import com.headout.dto.CityProfileDto;
import com.headout.dto.QuestionDto;
import com.headout.model.CityProfile;
import com.headout.service.CityProfileService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CityProfileServiceImp implements CityProfileService {

	private CityProfileDao dao;

	public CityProfileServiceImp(CityProfileDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public String addCityInfo(CityProfileDto profileDto) {
		if (profileDto == null) {
			return "City information is empty";
		}

		if (profileDto.getCity() == null)
			return "City is Empty";
		if (profileDto.getClues().isEmpty())
			return "Clues are empty";
		if (profileDto.getCountry() == null)
			return "Country is empty";
		if (profileDto.getFunFact().isEmpty())
			return "Fun fact is empty";
		if (profileDto.getTrivia().isEmpty())
			return "Trivia is empty";

		CityProfile profile = new CityProfile();
		profile.setCity(profileDto.getCity());
		profile.setCountry(profileDto.getCountry());
		profile.setClues(profileDto.getClues());
		profile.setFunFact(profileDto.getFunFact());
		profile.setTrivia(profileDto.getTrivia());

		dao.save(profile);

		return "City information added successfully";
	}

	@Override
	public boolean validateAnswer(String city, String clue) {
		if (city.isBlank()) {
			return false;
		}
		return dao.checkAnswer(city, clue);
	}

	@Override
	public QuestionDto generateQuestion() {
		return dao.generateQuestion();
	}

	@Override
	public List<String> getFunFacts(long id) {
		return dao.getFunFact(id);
	}

}
