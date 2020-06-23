package com.flightInventory.controllersTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.security.Principal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.flightInventory.api.DemoApiApplication;
import com.flightInventory.api.controllers.FlightController;
import com.flightInventory.api.dataModels.Flight;
import com.flightInventory.api.services.FlightService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DemoApiApplication.class)
@WebAppConfiguration
public class FlightControllerTest {

	@Autowired
	WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	
	@MockBean 
	FlightService flightService;
	
	@InjectMocks
	FlightController flightController;
	
	@Mock
	private Principal principal;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void checkFlightSourceNullDestinationNull() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/checkFlight")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request)
								.andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(400, status);
	}
	
	@Test
	public void checkFlightDestinationNull() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/checkFlight")
				.param("source", "Chennai")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request)
								.andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(400, status);
	}
	
	@Test
	public void checkFlightSourceNull() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/checkFlight")
				.param("destination", "Delhi")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request)
								.andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(400, status);
	}
	
	@Test
	public void bookFlightFIdNull() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.post("/bookFlight")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request)
								.andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(400, status);
	}
	
	@Test
	public void addFlightBodyNull() throws Exception {
		Flight flight = new Flight(63, "Guwahati", "Pondicherry", "JT6738", 3400, 150, 180);
		
		String userName = "User";
		when(principal.getName()).thenReturn(userName);
	    
		when(flightService.addFlight(flight)).thenReturn(true);
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/addFlight")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(request)
				.andReturn();
		
		int status = result.getResponse().getStatus();
		assertEquals(400, status);
	}
}
