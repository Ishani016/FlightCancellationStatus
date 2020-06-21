package com.flightInventory.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

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
import com.flightInventory.dataModels.UserEntity;
import com.flightInventory.repositories.UserRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DemoApiApplication.class)
@WebAppConfiguration
public class SpringUserDetailsTest {
	@InjectMocks
	SpringUserDetails detailsService;
	
	@MockBean
	UserRepository userRepo;
	
	@Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void getUserByUsernameTest() throws ClassNotFoundException, SQLException {
		UserEntity user = new UserEntity(453, "testUser", "jgjqqheg236576");
		when(userRepo.getUser(user.getUserName())).thenReturn(user);
		assertEquals(user, detailsService.getUserByUserName(user.getUserName()));
	}
}
