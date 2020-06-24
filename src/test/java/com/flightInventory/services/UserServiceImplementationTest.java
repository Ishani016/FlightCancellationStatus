package com.flightInventory.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.flightInventory.api.DemoApiApplication;
import com.flightInventory.api.dataModels.UserEntity;
import com.flightInventory.api.repositories.UserRepository;
import com.flightInventory.api.services.UserServiceImplementation;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DemoApiApplication.class)
@WebAppConfiguration
public class UserServiceImplementationTest {
	
	@InjectMocks
	UserServiceImplementation userService;
	
	@MockBean
	UserRepository userRepo;
	
	@MockBean
	BCryptPasswordEncoder passwordEncoder;
	
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void userSignUpTest() throws ClassNotFoundException, SQLException {
		UserEntity user = new UserEntity(567153, "testUser", "kjqkwjhwj");
		
		when(userRepo.save(user)).thenReturn(user);
		
		assertEquals(userService.userSignUp(user), user);
		
	}
	
	@Test
	public void deleteUserTest() throws ClassNotFoundException, SQLException {
		UserEntity user = new UserEntity(567153, "testUser", "kjqkwjhwj");
		
		userService.deleteUser(user.getUserId());
		
		assertNull(userRepo.getUser(user.getUserName()));
	}
	
	@Test
	public void addDocumentTest() {
		UserEntity user = new UserEntity(837, "user1", "bhsdgjahg");
		String docUrl = "https://abcd.com/8678168763.image_86234.jpg";
		user.setDocUrl(docUrl);
		
		when(userRepo.save(user)).thenReturn(user);
		assertNotNull(docUrl);
		
	}
}
