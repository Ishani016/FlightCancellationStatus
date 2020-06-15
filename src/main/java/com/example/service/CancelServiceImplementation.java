package com.example.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.dao.CancelDao;

public class CancelServiceImplementation implements CancelService {
	@Autowired
	CancelDao cancelDao;
	
	@Override
	public void cancelFlightForUser(Integer flightId, Integer userId) throws ClassNotFoundException {
		try {
			cancelDao.cancelFlight(flightId, userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
	@Override 
	public String getCancelStatus(Integer flightId, Integer userId) throws ClassNotFoundException{
		String status = "";
		try {
			status = cancelDao.cancelFlightStatus(flightId, userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
}
