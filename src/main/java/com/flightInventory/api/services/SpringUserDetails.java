package com.flightInventory.api.services;

import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.flightInventory.api.dataModels.UserEntity;
import com.flightInventory.api.repositories.UserRepository;

public class SpringUserDetails implements UserDetailsService{
	private Logger logger = LoggerFactory.getLogger(Logger.class);
	
	@Autowired
	UserRepository userRepo;

	@Cacheable(value = "user", key="#userName")
	public UserEntity getUserByUserName(String userName) 
			throws ClassNotFoundException, SQLException {
		try {
			UserEntity user = userRepo.getUser(userName);
			return user;
		} catch(Exception e) {
			logger.error("Error occurred while fetching the user "+userName);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		UserEntity user = new UserEntity();
		try {
			user = getUserByUserName(username);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new User(user.getUserName(), user.getPassword(), new ArrayList<>());
	}

}
