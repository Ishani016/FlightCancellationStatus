package com.flightInventory.api.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.flightInventory.api.services.CancelService;

@EnableWebMvc
@RestController
public class CancelFlightController {
	private Logger logger = LoggerFactory.getLogger(Logger.class);

	@Autowired
	private CancelService cancelService;
	
	@GetMapping("/cancel")
	public ResponseEntity<String> cancelFlight(@RequestParam(required=true) Integer flightId,
			@RequestParam(required=true) Integer userId, 
			Principal principal) throws ClassNotFoundException {
		if(principal.getName()==null)
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user");
		logger.info("Cancellation request received by "+userId);
		boolean condition = this.cancelService.cancelFlightForUser(flightId, userId);
		if(!condition) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Flight Cancellation Successful");
	}
	
	@GetMapping("/status")
	public ResponseEntity<String> cancelStatus(@RequestParam(name="flightId") Integer flightId, 
			@RequestParam(name="userId") Integer userId,
			Principal principal) throws ClassNotFoundException {
		if(principal.getName()==null)
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user");
		logger.info("Cancellation status request received by "+userId);
		String status = this.cancelService.getCancelStatus(flightId, userId);
		if(status==null)
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Invalid data");
		logger.info("Status for flight for "+flightId+" for user "+ userId+ ": "+ status);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(status);
	}
}
