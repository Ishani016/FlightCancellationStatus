package com.flightInventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightInventory.dataModels.Flight;
import com.flightInventory.dataModels.UserEntity;
import com.flightInventory.dataModels.UserFlight;
import com.flightInventory.repositories.FlightRepository;
import com.flightInventory.repositories.UserFlightRepository;
import com.flightInventory.repositories.UserRepository;

public class FlightServiceImplementation implements FlightService {
	private Logger logger = LoggerFactory.getLogger(Logger.class);
	
	@Autowired
	FlightRepository flightRepo;
	
	@Autowired
	UserFlightRepository userFlightRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public List<Flight> getFlights(String source, String destination) {
		List<Flight> flights = new ArrayList<>();
		flights = flightRepo.getAvailableFlights(source, destination);
		return flights;
	}

	@Override
	public boolean bookFlight(Integer fId, String userName) {
		try {
			UserEntity user = userRepo.getUser(userName);
			Optional<Flight> flight = flightRepo.findById(fId);
			if(flight.isPresent()) {
				if(flight.get().getCurrCapacity()>=0 && flight.get().getCurrCapacity()<flight.get().getMaxCapacity()) {
					UserFlight userFlight;
					userFlight = userFlightRepo.findUserByFlightId(fId, user.getUserId());
					if(userFlight==null) {
						userFlight = new UserFlight();
						Random random = new Random();
						userFlight.setFlightId(random.nextInt(1000000000));
						userFlight.setFId(fId);
						userFlight.setUserId(user.getUserId());
					}
					Flight currFlight = flight.get();
					Integer currCapacity = currFlight.getCurrCapacity();
					currCapacity--;
					currFlight.setCurrCapacity(currCapacity);
					flightRepo.save(currFlight);
					logger.info("Flight: "+currFlight);
					userFlight.setBookingStatus("Confirmed");
					userFlightRepo.save(userFlight); 
					logger.info("Saving "+userFlight +" to the db");
					return true;
				}
			}
			return true;
		} catch (Exception e) {
			logger.error("Booking cannot be completed");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addFlight(Flight flight) {
		try {
			Random random = new Random();
			Integer id = flightRepo.findFIdByFlightName(flight.getFlightName());
			if(id==null) {
				id = random.nextInt(100000000);
			}
			flight.setFId(id);
			flightRepo.save(flight);
			return true;
		} catch(Exception e) {
			logger.error("Flight cannot be added ");
			e.printStackTrace();
		}
		return false;
	}
}
