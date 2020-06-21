package com.flightInventory.repositories;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.flightInventory.api.DemoApiApplication;
import com.flightInventory.dataModels.Flight;

//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = DemoApiApplication.class)
//@RunWith(SpringRunner.class)
@DataJpaTest
public class FlightRepositoryTest {

	@Autowired
	private FlightRepository flightRepo;
	
	@Test
	public void getAvailableFlightsTest() {
		List<Flight> flights = new ArrayList<>();
		flights.add(new Flight(12, "Pune", "Patna", "SPJ453", 5678, 29, 186));
		flights.add(new Flight(35, "Pune", "Patna", "VST8263", 2890, 78, 180));

		System.out.println("I am flight"+flightRepo);
		List<Flight> availFlights = flightRepo.getAvailableFlights("Pune", "Patna");
		assertEquals(2, availFlights.size());
	}
	
}
