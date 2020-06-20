package com.flightInventory.services;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.flightInventory.dataModels.UserEntity;
import com.flightInventory.repositories.UserRepository;

public class SpringUserDetails implements UserDetailsService{
	@Autowired
	UserRepository userRepo;

	private UserEntity getUserByUserName(String userName) throws ClassNotFoundException, SQLException {
		System.out.println(userName);
		UserEntity user = userRepo.getUser(userName);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
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
