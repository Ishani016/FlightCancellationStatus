package com.flightInventory.repositories;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.flightInventory.api.dataModels.Flight;
import com.flightInventory.api.repositories.FlightRepository;

//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = DemoApiApplication.class)
//@RunWith(SpringRunner.class)
@DataJpaTest
public class FlightRepositoryTest {

	private FlightRepository flightRepo = new FlightRepository() {
	
		
		@Override
		public Iterable<Flight> findAllById(Iterable<Integer> ids) {
			return null;
		}
		
		@Override
		public Iterable<Flight> findAll() {
			return null;
		}
		
		@Override
		public boolean existsById(Integer id) {
			return false;
		}
		
		@Override
		public void deleteById(Integer id) {
		}
		
		@Override
		public void deleteAll(Iterable<? extends Flight> entities) {
		}
		
		@Override
		public void deleteAll() {
		}
		
		@Override
		public void delete(Flight entity) {
		}
		
		@Override
		public long count() {
			return 0;
		}
		
		@Override
		public List<Flight> getAvailableFlights(String source, String destination) {
			List<Flight> flights = new ArrayList<>();
			flights.add(new Flight(12, "Pune", "Patna", "SPJ453", 5678, 29, 186));
			flights.add(new Flight(35, "Pune", "Patna", "VST8263", 2890, 78, 180));
			return flights;
		}
		
		@Override
		public Integer findFIdByFlightName(String flightName) {
			Flight flight = new Flight(29, "Pune", "Patna", "SPJ453", 5678, 123, 186);
			if(flightName==flight.getFlightName())
				return flight.getFId();
			return null;
		}

		@Override
		public <S extends Flight> S save(S entity) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <S extends Flight> Iterable<S> saveAll(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Optional<Flight> findById(Integer id) {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	@Test
	public void getAvailableFlightsTest() {
		List<Flight> flights = new ArrayList<>();
		flights.add(new Flight(12, "Pune", "Patna", "SPJ453", 5678, 29, 186));
		flights.add(new Flight(35, "Pune", "Patna", "VST8263", 2890, 78, 180));
		
		List<Flight> availFlights = flightRepo.getAvailableFlights("Pune", "Patna");
		assertEquals(2, availFlights.size());
	}
	
	@Test
	public void findFIdByFlightNameTest() {
		Integer fId = 29;
		
		Integer id = flightRepo.findFIdByFlightName("SPJ453");
		assertEquals(fId, id);
	}
	
}
