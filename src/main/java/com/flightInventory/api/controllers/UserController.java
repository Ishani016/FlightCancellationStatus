package com.flightInventory.api.controllers;

import java.security.Principal;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightInventory.api.customer.userAuth.AuthenticationRequest;
import com.flightInventory.api.customer.userAuth.AuthenticationResponse;
import com.flightInventory.api.customer.userAuth.JwtUtil;
import com.flightInventory.api.customer.userAuth.SecurityConfig;
import com.flightInventory.api.dataModels.UserEntity;
import com.flightInventory.api.services.SpringUserDetails;
import com.flightInventory.api.services.UserService;

@Import(SecurityConfig.class)
@RestController
public class UserController {
	private Logger logger = LoggerFactory.getLogger(Logger.class);
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private SpringUserDetails userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/signUp")
	public ResponseEntity<String> userSignUp(@RequestBody @Validated UserEntity user) throws ClassNotFoundException, SQLException {
		if(user.getUserName()==null || user.getPassword()==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient data!");
		}
		this.userService.userSignUp(user);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("User sign up successful!");
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteAccount(@RequestParam(required=true) Integer id, 
			Principal principal) throws ClassNotFoundException, SQLException{
		if(principal.getName()==null)
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user");
		if(id==null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No userId provided");
		logger.info("Account delete requested by "+id);
		userService.deleteUser(id);
		logger.info("Account for user "+id+" deleted successfully!");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(id + " has been deleted!");
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) throws Exception {
		try{
			authManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
				);
		}catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect username or password");
		}
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUserName());
		
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new AuthenticationResponse(jwt));
	}
	
}
