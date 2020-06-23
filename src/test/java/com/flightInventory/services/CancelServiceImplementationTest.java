package com.flightInventory.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.flightInventory.api.DemoApiApplication;
import com.flightInventory.api.dataModels.UserFlight;
import com.flightInventory.api.repositories.UserFlightRepository;
import com.flightInventory.api.services.CancelServiceImplementation;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DemoApiApplication.class)
@WebAppConfiguration
public class CancelServiceImplementationTest {
	@InjectMocks
	CancelServiceImplementation cancelService;
	
	@MockBean
	UserFlightRepository userFlightRepo;
	
	@Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void cancelFlightForUserTest() throws ClassNotFoundException {
		UserFlight userFlight = new UserFlight();
		userFlight.setBookingStatus("Confirmed");
		userFlight.setFlightId(65465);
		userFlight.setFId(787);
		userFlight.setUserId(4243);
		userFlight.setCancellationStatus("Cancelled");
		when(userFlightRepo.save(userFlight)).thenReturn(userFlight);
		assertEquals(true, cancelService.cancelFlightForUser(65465, 4243));
	}
	
	@Test
	public void getCancelStatusTest() throws ClassNotFoundException {
		UserFlight userFlight = new UserFlight();
		userFlight.setBookingStatus("Confirmed");
		userFlight.setFlightId(1234);
		userFlight.setFId(1234);
		userFlight.setUserId(5678);
		userFlight.setCancellationStatus("Not Cancelled");
		when(userFlightRepo.findCancelFlightStatus(1234, 5678)).thenReturn("Not Cancelled");
		assertEquals(userFlight.getCancellationStatus(), 
				cancelService.getCancelStatus(1234, 5678));
	}
}
