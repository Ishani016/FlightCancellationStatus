package com.flightInventory.controllersTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
import com.flightInventory.controllers.UserLoginController;
import com.flightInventory.dataModels.UserEntity;
import com.flightInventory.services.UserService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DemoApiApplication.class)
@WebAppConfiguration
public class UserLoginControllerTest {
	@Autowired
	WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	
	@InjectMocks
	UserLoginController userController;

	@MockBean
	UserService userService;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void userSignUpUserNameNullUserPasswordNull() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.post("/signUp")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request)
									.andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(400, status);
	}
	
	@Test
	public void userDelete_userIdNull() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.delete("/delete")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request)
									.andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(400, status);
	}
}
