package com.example.api;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.dao.CancelDao;
import com.example.dao.UserDao;
import com.example.service.CancelServiceImplementation;
import com.example.service.SpringUserDetails;
import com.example.service.UserServiceImplementation;

@SpringBootApplication
//@ComponentScan("com.example.api")
//@ComponentScan("com.example.services")
public class DemoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApiApplication.class, args);
	}

	@Bean
    public CancelServiceImplementation cancelServiceImplementation() {
        return new CancelServiceImplementation();
    }
	
	@Bean
	public CancelDao cancelDao() {
		return new CancelDao();
	}
	
	@Bean
    public UserDao userDao() throws SQLException {
        return new UserDao();
    }
	
	@Bean
    public UserServiceImplementation userServiceImplementation() {
        return new UserServiceImplementation();
    }
	
	@Bean
	public SpringUserDetails springUserService() {
		return new SpringUserDetails();	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
