package com.flightInventory.controllersTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
import com.flightInventory.api.controllers.AwsBucketController;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DemoApiApplication.class)
@WebAppConfiguration
public class AwsBucketControllerTest {

	@Autowired
	WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	
	@InjectMocks
	AwsBucketController awsBucketController;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void uploadTestBodyNull() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/cancel")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request).andReturn();
		
		int status = result.getResponse().getStatus();
		assertEquals(400, status);
	}
	
	@Test
	public void deleteFileTestUrlNull() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.delete("/deleteDoc")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andReturn();
		
		int status = result.getResponse().getStatus();
		assertEquals(400, status);
	}
}
