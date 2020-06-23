package com.flightInventory.api.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.flightInventory.api.customer.userAuth.JwtUtil;
import com.flightInventory.api.dataModels.Flight;
import com.flightInventory.api.services.FlightService;
import com.flightInventory.api.services.SpringUserDetails;

@EnableWebMvc
@RestController
public class FlightController {
	private Logger logger = LoggerFactory.getLogger(Logger.class);
	
	@Autowired
	FlightService flightService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	SpringUserDetails userDetailsService;
	
	@GetMapping("/checkFlight")
	public ResponseEntity<List<Flight>> getFlights(@RequestParam(required=true) String source,
			@RequestParam String destination) {
		logger.info("Flight requested from "+source+" to "+destination);
		List<Flight> availableFlights = new ArrayList<>();
		availableFlights = flightService.getFlights(source, destination);
		logger.info(availableFlights.size() + " available from "+source+" to "+destination);
		if(availableFlights.isEmpty())
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(availableFlights);
	}
	
	@PostMapping("/bookFlight")
	public ResponseEntity<String> bookFlight(@RequestParam(required=true) Integer fId,
			Principal principal) {
		String userName = principal.getName();
		if(userName==null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user");
		 UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		 logger.info(userDetails.getUsername()+" requesting for flight");
		 boolean condition = flightService.bookFlight(fId, userName);
		 if(!condition)
			 return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Booking cannot be confirmed");
		 return ResponseEntity.status(HttpStatus.ACCEPTED).body("Booking confirmed!");
	}
	
	@PostMapping("/addFlight")
	public ResponseEntity<String> addFlight(@RequestBody Flight flight, Principal principal) {
		String userName = principal.getName();
		if(userName==null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user");
		boolean condition = flightService.addFlight(flight);
		if(!condition)
			 return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Flight cannot be added");
		 return ResponseEntity.status(HttpStatus.ACCEPTED).body("Flight added!");
	}
}
