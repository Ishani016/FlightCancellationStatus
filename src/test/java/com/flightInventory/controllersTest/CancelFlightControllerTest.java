package com.flightInventory.controllersTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.flightInventory.api.DemoApiApplication;
import com.flightInventory.controllers.CancelFlightController;
import com.flightInventory.dataModels.UserFlight;
import com.flightInventory.services.CancelService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DemoApiApplication.class)
@WebAppConfiguration
public class CancelFlightControllerTest {

	@Autowired
	WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@MockBean
	CancelService cancelService;
	
	@InjectMocks
	CancelFlightController cancelFlightController;

	String cancellationStatus = "Cancelled";
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void shouldCancelFlightForFlightIdAndUserIdInavlidData() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/cancel")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request)
				.andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(400, status);
	}
	
	@Test
	public void userCancelFlightFlightIdNull() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/cancel")
				.param("flightId", "657657")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request)
										.andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(400, status);
	}
	
	@Test
	public void userCancelFlightUserIdNull() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/cancel")
				.param("userId", "43542354")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request)
				.andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(400, status);
	}
	
	@Test
	public void userCheckStatusUserIdNullFlightIdNull() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/status")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request)
									.andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(400, status);
	}
	
	@Test
	public void userCheckStatusUserIdNull() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/status")
				.param("flightId", "7657567")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request)
									.andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(400, status);
	}
	
	@Test
	public void userCheckStatusFlightIdNull() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/status")
				.param("userId", "54353453")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request)
									.andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(400, status);
	}
}
