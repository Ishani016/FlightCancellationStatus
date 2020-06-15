package com.example.api;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dataModels.UserEntity;
import com.example.service.UserService;

@RestController
public class UserLogin {
	private Logger logger = LoggerFactory.getLogger(Logger.class);
	
	UserService userService;

	public UserLogin(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/signUp")
	public ResponseEntity<String> userSignUp(@RequestBody @Validated UserEntity user) throws ClassNotFoundException, SQLException {
		if(user.getUserName()==null || user.getPassword()==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient data!");
		}
		this.userService.userSignUp(user);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("User sign up successful!");
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteAccount(@RequestParam(required=true) Integer id) throws ClassNotFoundException, SQLException{
		if(id==null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No userId provided");
		logger.info("Account delete requested by "+id);
		userService.deleteUser(id);
		logger.info("Account for user "+id+" deleted successfully!");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(id + " has been deleted!");
	}
}
