package com.flightInventory.services;

import java.sql.SQLException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.flightInventory.dataModels.Flight;
import com.flightInventory.dataModels.UserEntity;
import com.flightInventory.dataModels.UserFlight;
import com.flightInventory.repositories.FlightRepository;
import com.flightInventory.repositories.UserFlightRepository;
import com.flightInventory.repositories.UserRepository;

public class CancelServiceImplementation implements CancelService {
	private Logger logger = LoggerFactory.getLogger(Logger.class);
	
	@Autowired
	UserFlightRepository userFlightRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	FlightRepository flightRepo;
	
	@Override
	public boolean cancelFlightForUser(Integer fId, Integer userId) throws ClassNotFoundException {
		try {
			Optional<UserEntity> user = userRepo.findById(userId);
			if(user.get()==null || user.get().getUserName()==null) {
				logger.error("No user found for "+userId);
				return false;
			}
			UserFlight userFlight = userFlightRepo.findUserByFlightId(fId, userId);
			userFlight.setCancellationStatus("Cancelled");
			Optional<Flight> flight = flightRepo.findById(fId);
			if(!flight.isPresent()) {
				logger.error("No flight found for "+fId);
				return false;
			}
			Flight currFlight = flight.get();
			Integer currCapacity = currFlight.getCurrCapacity();
			currCapacity++;
			currFlight.setCurrCapacity(currCapacity);
			flightRepo.save(currFlight);
			userFlightRepo.save(userFlight);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	@Override 
	public String getCancelStatus(Integer fId, Integer userId) throws ClassNotFoundException{
		String status = "Not Cancelled";
		try {
			Optional<UserEntity> user = userRepo.findById(userId);
			if(user.get()==null || user.get().getUserName()==null) {
				logger.error("No user found for "+userId);
				return null;
			}
			status = userFlightRepo.findCancelFlightStatus(fId, userId);
			if(status==null)
				status = "Not Cancelled";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
