package com.example.api;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.dataModels.UserEntity;

@SpringBootTest
public class UserLoginTest {
	
	@Test
	public void userSignUp_userIdNull() {
		UserEntity user = new UserEntity();
		user.setPassword("uywtuyeq76876");
		user.setUserName("user");
		assertNotNull(user);
	}
	
}
