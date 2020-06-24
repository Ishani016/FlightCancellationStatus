package com.flightInventory.api.services;

import java.sql.SQLException;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.flightInventory.api.dataModels.UserEntity;
import com.flightInventory.api.repositories.UserRepository;

public class UserServiceImplementation implements UserService {

	private Logger logger = LoggerFactory.getLogger(Logger.class);
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserEntity userSignUp(UserEntity user) throws ClassNotFoundException, SQLException {
		try {
			Random random = new Random();Integer id = random.nextInt(100000000);
			user.setUserId(id);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepo.save(user);
			logger.info(user.getUserName() + "added successfully");
			return user;
		} catch(Exception e) {
			logger.error("Error occurred while adding user "+ user.getUserName());
			e.printStackTrace();
		}
		return null;
	}
		

	@Override
	public void deleteUser(Integer id) throws ClassNotFoundException, SQLException {
		try {
			userRepo.deleteById(id);
			logger.info(id+" deleted successfully");
		} catch(Exception e) {
			logger.error("Error occurred while deleting user");
			e.printStackTrace();
		}
	}

	@Override
	public void addDocumentUrl(String fileUrl, String userName) {
		try {
			UserEntity user = userRepo.getUser(userName);
			user.setDocUrl(fileUrl);
			userRepo.save(user);
		} catch(Exception e) {
			logger.error("Document URL cannot be added");
			e.printStackTrace();
		}
	}
}
