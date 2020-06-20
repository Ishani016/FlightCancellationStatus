package com.flightInventory.services;

import java.sql.SQLException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.flightInventory.dataModels.UserEntity;
import com.flightInventory.repositories.UserRepository;

public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public void userSignUp(UserEntity user) throws ClassNotFoundException, SQLException {
		Random random = new Random();
		Integer id = random.nextInt(100000000);
		user.setUserId(id);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
		return;
	}

	@Override
	public void deleteUser(Integer id) throws ClassNotFoundException, SQLException {
		userRepo.deleteById(id);
		return;
	}
}
