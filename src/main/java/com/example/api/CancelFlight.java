package com.example.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.CancelService;

@RestController
public class CancelFlight {
	private Logger logger = LoggerFactory.getLogger(Logger.class);

//	@Autowired
	private CancelService cancelService;
	
	public CancelFlight(CancelService cancelService) {
		this.cancelService = cancelService;
	}
	
	@GetMapping("/cancel")
	public ResponseEntity<String> cancelFlight(@RequestParam(required=true) Integer flightId,
			@RequestParam(required=true) Integer userId) throws ClassNotFoundException {

		//get the flight for the given flight id
		logger.info("Cancellation request received by "+userId);
		this.cancelService.cancelFlightForUser(flightId, userId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Flight Cancellation Successful");
	}
	
	@GetMapping("/status")
	public ResponseEntity<String> cancelStatus(@RequestParam(name="flightId") Integer flightId, 
			@RequestParam(name="userId") Integer userId) throws ClassNotFoundException {
		logger.info("Cancellation status request received by "+userId);
		String status = this.cancelService.getCancelStatus(flightId, userId);
		if(status.isEmpty())
			status = "None";
		logger.info("Status for flight for "+flightId+" for user "+ userId+ ": "+ status);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(status);
	}
}
