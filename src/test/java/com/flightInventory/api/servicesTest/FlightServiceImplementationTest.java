package com.flightInventory.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.flightInventory.api.DemoApiApplication;
import com.flightInventory.api.dataModels.Flight;
import com.flightInventory.api.dataModels.UserFlight;
import com.flightInventory.api.repositories.FlightRepository;
import com.flightInventory.api.repositories.UserFlightRepository;
import com.flightInventory.api.repositories.UserRepository;
import com.flightInventory.api.services.FlightServiceImplementation;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DemoApiApplication.class)
@WebAppConfiguration
public class FlightServiceImplementationTest {
	@InjectMocks
	FlightServiceImplementation flightService;
	
	@MockBean
	FlightRepository flightRepo;
	
	@MockBean
	UserFlightRepository userFlightRepo;
	
	@MockBean
	UserRepository userRepo;
	
	@Mock 
	Principal principal;
	
	@Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void getFlightsTest() throws NullPointerException {
		List<Flight> flights = new ArrayList<>();
		flights.add(new Flight(34, "Chennai", "Mumbai", "HY6876", 7866, 20, 186));
		flights.add(new Flight(68, "Chennai", "Mumbai", "IND8163", 1200, 120, 192));
		
		when(flightRepo.getAvailableFlights("Chennai", "Mumbai")).thenReturn(flights);
		
		assertEquals(flights, flightService.getFlights("Chennai", "Mumbai"));
	}

	@Test
	public void bookFlightTest() {
		UserFlight userFlight = new UserFlight();
		userFlight.setBookingStatus("Confirmed");
		userFlight.setFlightId(65465);
		userFlight.setFId(787);
		userFlight.setUserId(4243);
		userFlight.setCancellationStatus("Cancelled");
		
		Flight currFlight = new Flight(787, "Chennai", "Mumbai", "HY6876", 7866, 20, 186);

		String userName = "user2";
		when(principal.getName()).thenReturn(userName);
		
		Integer capacity =  currFlight.getCurrCapacity();
		currFlight.setCurrCapacity(capacity-1);
		when(flightRepo.save(currFlight)).thenReturn(currFlight);
		
		when(userFlightRepo.save(userFlight)).thenReturn(userFlight);
		assertTrue(flightService.bookFlight(787,userName));
	}
	
}
