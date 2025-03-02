package com.headout.dao;

import java.util.List;

import com.headout.model.Destination;

public interface DestinationDao {

	public String addDestinations(Destination destination);

	public List<String> getThreeCities();

}
