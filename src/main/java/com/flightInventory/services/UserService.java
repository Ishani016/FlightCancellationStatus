package com.flightInventory.services;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.flightInventory.dataModels.UserEntity;

@Service
public interface UserService {
	void userSignUp(UserEntity user) throws ClassNotFoundException, SQLException;
	void deleteUser(Integer id) throws ClassNotFoundException, SQLException;
}
