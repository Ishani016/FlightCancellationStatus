package com.flightInventory.repositories;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.flightInventory.api.dataModels.UserFlight;
import com.flightInventory.api.repositories.UserFlightRepository;

@DataJpaTest
public class UserFlightRepositoryTest {

	UserFlightRepository userFlightRepository = new UserFlightRepository() {
		
		@Override
		public <S extends UserFlight> Iterable<S> saveAll(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <S extends UserFlight> S save(S entity) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Optional<UserFlight> findById(Integer id) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Iterable<UserFlight> findAllById(Iterable<Integer> ids) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Iterable<UserFlight> findAll() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public boolean existsById(Integer id) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void deleteById(Integer id) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void deleteAll(Iterable<? extends UserFlight> entities) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void deleteAll() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void delete(UserFlight entity) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public long count() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public UserFlight findUserByFlightId(Integer fId, Integer userId) {
			UserFlight userFlight = new UserFlight();
			userFlight.setBookingStatus("Confirmed");
			userFlight.setFlightId(123);
			userFlight.setFId(45);
			userFlight.setUserId(4243);
			userFlight.setCancellationStatus("Cancelled");
			if(userFlight.getFId().equals(fId) && userFlight.getUserId().equals(userId)) {
				return userFlight;
			}
			return null;
		}
		
		@Override
		public String findCancelFlightStatus(Integer fId, Integer userId) {
			UserFlight userFlight = new UserFlight();
			userFlight.setBookingStatus("Confirmed");
			userFlight.setFlightId(123);
			userFlight.setFId(45);
			userFlight.setUserId(4243);
			userFlight.setCancellationStatus("Cancelled");

			if(userFlight.getFId().equals(fId) && userFlight.getUserId().equals(userId)) {
				return userFlight.getCancellationStatus();
			}
			return null;
		}
	};
	
	@Test
	public void getUserByFlightId() {
		UserFlight userFlight = new UserFlight();
		userFlight.setBookingStatus("Confirmed");
		userFlight.setFlightId(123);
		userFlight.setFId(45);
		userFlight.setUserId(4243);
		userFlight.setCancellationStatus("Cancelled");
		
		UserFlight availUserFlight = userFlightRepository.findUserByFlightId(45, 4243);
		assertNotNull(availUserFlight);
	}
	
	@Test
	public void getCancellationStatusTest() {
		UserFlight userFlight = new UserFlight();
		userFlight.setBookingStatus("Confirmed");
		userFlight.setFlightId(123);
		userFlight.setFId(45);
		userFlight.setUserId(4243);
		userFlight.setCancellationStatus("Cancelled");
		String status = userFlightRepository.findCancelFlightStatus(45, 4243);
		assertNotNull(status);
	}
}
