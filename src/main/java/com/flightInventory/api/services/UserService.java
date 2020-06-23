package com.flightInventory.api.services;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.flightInventory.api.dataModels.UserEntity;

@Service
public interface UserService {
	void userSignUp(UserEntity user) throws ClassNotFoundException, SQLException;
	void deleteUser(Integer id) throws ClassNotFoundException, SQLException;
	void addDocumentUrl(String fileUrl, String userName);
}
