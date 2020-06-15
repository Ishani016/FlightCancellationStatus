package com.example.service;

import org.springframework.stereotype.Service;

@Service
public interface CancelService {
	boolean cancelFlightForUser(Integer flightId, Integer userId) throws ClassNotFoundException;
	String getCancelStatus(Integer flightId, Integer userId) throws ClassNotFoundException;
}
