package com.example.service;

import java.sql.SQLException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.dao.UserDao;
import com.example.dataModels.UserEntity;

public class UserServiceImplementation implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public void userSignUp(UserEntity user) throws ClassNotFoundException, SQLException {
		Random random = new Random();
		Integer id = random.nextInt(100000000);
		user.setUserId(id);
		userDao.signUp(user);
		return;
	}

	@Override
	public void deleteUser(Integer id) throws ClassNotFoundException, SQLException {
		userDao.deleteUser(id);
		return;
	}
}
