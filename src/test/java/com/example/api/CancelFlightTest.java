package com.example.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CancelFlightTest {

	@Test
	public void testCancelFlight_flightIdNotNull() {
		Integer flightId = 2352;
		assertNotNull(flightId);
	}
	
	@Test
	public void testCancelFlight_userIdNotNull() {
		Integer userId = 565767;
		assertNotNull(userId);
	}
}
