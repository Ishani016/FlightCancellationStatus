package com.flightInventory.api.services;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.flightInventory.api.dataModels.Flight;

@Service
public interface FlightService {

	@Cacheable(value="availableFlights", key="{ #source, #destination}")
	List<Flight> getFlights(String source, String destination);
	boolean bookFlight(Integer fId, String userName);
	Flight addFlight(Flight flight);
}
