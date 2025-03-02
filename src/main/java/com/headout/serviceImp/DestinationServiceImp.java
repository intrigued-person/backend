package com.headout.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.headout.dao.DestinationDao;
import com.headout.model.Destination;
import com.headout.service.DestinationService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DestinationServiceImp implements DestinationService {

	@Autowired
	private DestinationDao dao;

	@Override
	public String addDestinations(Destination destination) {
		if (destination != null) {
			return dao.addDestinations(destination);
		}
		return "Service layer (desination not added)";
	}

}
