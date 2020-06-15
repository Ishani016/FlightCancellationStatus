package com.example.service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.dao.CancelDao;
import com.example.dataModels.UserEntity;

public class CancelServiceImplementation implements CancelService {
	private Logger logger = LoggerFactory.getLogger(Logger.class);
	
	@Autowired
	CancelDao cancelDao;
	
	@Override
	public boolean cancelFlightForUser(Integer flightId, Integer userId) throws ClassNotFoundException {
		try {
			UserEntity user = cancelDao.getUserById(userId);
			if(user.getUserName()==null) {
				logger.error("No user found for "+userId);
				return false;
			}
			cancelDao.cancelFlight(flightId, userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	@Override 
	public String getCancelStatus(Integer flightId, Integer userId) throws ClassNotFoundException{
		String status = "";
		try {
			UserEntity user = cancelDao.getUserById(userId);
			if(user.getUserName()==null) {
				logger.error("No user found for "+userId);
				return null;
			}
			status = cancelDao.cancelFlightStatus(flightId, userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
}
