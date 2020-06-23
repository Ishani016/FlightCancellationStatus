package com.flightInventory.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flightInventory.api.dataModels.Flight;

@Service
public interface FlightService {
	List<Flight> getFlights(String source, String destination);
	boolean bookFlight(Integer fId, String userName);
	boolean addFlight(Flight flight);
}
