package com.headout.daoImp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.headout.dao.DestinationDao;
import com.headout.model.Destination;

import jakarta.persistence.EntityManager;

@Repository
public class DestinationDaoImp implements DestinationDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public String addDestinations(Destination destination) {

		if (destination != null) {
			entityManager.persist(destination);
			return "Destination added successfully";
		}

		return "Failed to add Destination";

	}

	public long count() {
		String hql = "SELECT COUNT(e) FROM Destination e";
		Long size = entityManager.createQuery(hql, Long.class).getSingleResult();
		return size;
	}

	public List<Long> generateRandomDestinationIds() {
		long count = count();
		if (count <= 3) {
			String hql = "SELECT d.destinationId FROM Destination d";
			return entityManager.createQuery(hql, Long.class).getResultList();
		} else {
			String hql = "SELECT d.destinationId FROM Destination d";
			List<Long> allIds = entityManager.createQuery(hql, Long.class).getResultList();
			Collections.shuffle(allIds);
			return allIds.subList(0, 3);
		}
	}

	@Override
	public List<String> getThreeCities() {
		List<Long> destinationIds = generateRandomDestinationIds();
		List<String> cities = new ArrayList<>();

		Destination destination;

		for (Long id : destinationIds) {
			destination = entityManager.find(Destination.class, id);
			cities.add(destination.getCities());
		}
		return cities;
	}

}
