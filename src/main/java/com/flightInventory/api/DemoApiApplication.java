package com.flightInventory.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.flightInventory.services.CancelServiceImplementation;
import com.flightInventory.services.FlightServiceImplementation;
import com.flightInventory.services.SpringUserDetails;
import com.flightInventory.services.UserServiceImplementation;
import com.flightInvetory.customer.userAuth.JwtRequest;
import com.flightInvetory.customer.userAuth.JwtUtil;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories("com.flightInventory.repositories")
@ComponentScan("com.flightInventory.*")
@EntityScan("com.flightInventory.*")
public class DemoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApiApplication.class, args);
	}
	
	@Bean
    public CancelServiceImplementation cancelServiceImplementation() {
        return new CancelServiceImplementation();
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
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtRequest jwtFilter() {
		return new JwtRequest();
	}

	@Bean
	public FlightServiceImplementation flightService() {
		return new FlightServiceImplementation();
	}
	
	@Bean 
	public JwtUtil jwtUtil() {
		return new JwtUtil();
	}
}
