package com.flightInventory.repositories;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserFlightRepositoryTest {

	@Autowired
	UserFlightRepository userFlightRepository;
	
	@Test
	public void getCancellationStatusTest() {
		System.out.println("I am userFlight"+userFlightRepository);
		String status = userFlightRepository.findCancelFlightStatus(123, 242);
		assertNotNull(status);
	}
}
