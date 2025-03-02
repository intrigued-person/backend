package com.headout;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.headout.dao.DestinationDao;
import com.headout.model.Destination;
import com.headout.serviceImp.DestinationServiceImp;

public class DestinationServiceImpTest {

    @InjectMocks
    private DestinationServiceImp service;

    @Mock
    private DestinationDao dao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddDestinations_NullDestination() {
        String response = service.addDestinations(null);
        assertEquals("Service layer (desination not added)", response);
    }

    @Test
    public void testAddDestinations_ValidDestination() {
        Destination destination = new Destination();
        destination.setCities("New York");

        when(dao.addDestinations(destination)).thenReturn("Destination added successfully");

        String response = service.addDestinations(destination);
        assertEquals("Destination added successfully", response);
        verify(dao, times(1)).addDestinations(destination);
    }

    @Test
    public void testAddDestinations_InvalidDestination() {
        // In this scenario, you're actually testing the default 'null' case above;
        // hence we won't cover this again as it's already tested.
        
        // However, you can have this point:
        Destination destination = new Destination(); // empty destination, if relevant
        when(dao.addDestinations(destination)).thenReturn("Failed to add Destination");
        
        String response = service.addDestinations(destination);
        assertEquals("Failed to add Destination", response);
        verify(dao, times(1)).addDestinations(destination);
    }
}