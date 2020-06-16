package com.example.api;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dataModels.UserEntity;
import com.example.service.SpringUserDetails;
import com.example.service.UserService;
import com.example.userAuth.AuthenticationRequest;
import com.example.userAuth.AuthenticationResponse;
import com.example.userAuth.JwtUtil;
import com.example.userAuth.SecurityConfig;

@Import(SecurityConfig.class)
@RestController
public class UserLogin {
	private Logger logger = LoggerFactory.getLogger(Logger.class);
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private SpringUserDetails userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
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
