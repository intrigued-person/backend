package com.headout.daoImp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.headout.dao.CityProfileDao;
import com.headout.dao.DestinationDao;
import com.headout.dto.QuestionDto;
import com.headout.model.CityProfile;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class CityProfileDaoImp implements CityProfileDao {

	private EntityManager entityManager;

	private DestinationDao destinationDao;

	public CityProfileDaoImp(EntityManager entityManager, DestinationDao destinationDao) {
		super();
		this.entityManager = entityManager;
		this.destinationDao = destinationDao;
	}

	@Override
	public String save(CityProfile cityProfile) {
		if (cityProfile != null) {
			entityManager.persist(cityProfile);
			return "City Profile Added";
		}
		return "Error while adding city profile in DB";
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkAnswer(String city, String clue) {
		String hql = "SELECT c.clues FROM CityProfile c where c.city = :city";
		List<List<String>> results = entityManager.createQuery(hql).setParameter("city", city).getResultList();

		if (!results.isEmpty()) {
			List<String> clues = results.get(0);
			return clues.contains(clue);
		}

		return false;
	}

	public long count() {
		String hql = "SELECT COUNT(c) FROM CityProfile c";
		Long size = entityManager.createQuery(hql, Long.class).getSingleResult();
		return size;
	}

	public long generateRandomQuestionId() {
		long count = count();

		if (count < 1) {
			return 0L;
		} else {
			String hql = "SELECT c.id FROM CityProfile c";
			TypedQuery<Long> query = entityManager.createQuery(hql, Long.class);

			int randomIndex = (int) (Math.random() * count);
			query.setFirstResult(randomIndex);
			query.setMaxResults(1);

			Long id = query.getSingleResult();

			return id != null ? id : 0L;
		}
	}

	@Override
	public QuestionDto generateQuestion() {
		Long id = generateRandomQuestionId();
		CityProfile city = entityManager.find(CityProfile.class, id);
		if (city != null) {
			QuestionDto dto = new QuestionDto();
			dto.setId(id);

			String c = city.getCity();

			List<String> cities = destinationDao.getThreeCities();
			cities.add(c);

			Collections.shuffle(cities);

			dto.setClues(city.getClues());
			dto.setCities(cities);
			return dto;
		}
		return new QuestionDto();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getFunFact(long id) {
		String hql = "SELECT c.funFact FROM CityProfile c WHERE c.id = :id";

		List<List<String>> resultList = entityManager.createQuery(hql).setParameter("id", id).getResultList();

		if (!resultList.isEmpty() && !resultList.get(0).isEmpty()) {
			return resultList.get(0);
		} else {
			return new ArrayList<>();
		}
	}

}
