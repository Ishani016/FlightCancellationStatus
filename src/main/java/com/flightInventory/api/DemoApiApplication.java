package com.flightInventory.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.flightInventory.api.customer.userAuth.JwtRequest;
import com.flightInventory.api.services.AmazonClientServiceImplementation;
import com.flightInventory.api.services.CancelServiceImplementation;
import com.flightInventory.api.services.FlightServiceImplementation;
import com.flightInventory.api.services.SpringUserDetails;
import com.flightInventory.api.services.UserServiceImplementation;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories("com.flightInventory.api.repositories")
@ComponentScan("com.flightInventory.*")
@EntityScan("com.flightInventory.*")
@Configuration
@EnableCaching
@EnableEurekaServer
public class DemoApiApplication {
	 
	public static void main(String[] args) {
		SpringApplication.run(DemoApiApplication.class, args);
	}
	
	@Bean 
	public AmazonClientServiceImplementation amazonClientService() {
		return new AmazonClientServiceImplementation();
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

}
