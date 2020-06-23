package com.flightInventory.repositories;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.flightInventory.api.dataModels.UserEntity;
import com.flightInventory.api.repositories.UserRepository;

@DataJpaTest
public class UserRepositoryTest {

	UserRepository userRepo = new UserRepository() {
		
		@Override
		public <S extends UserEntity> Iterable<S> saveAll(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <S extends UserEntity> S save(S entity) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Optional<UserEntity> findById(Integer id) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Iterable<UserEntity> findAllById(Iterable<Integer> ids) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Iterable<UserEntity> findAll() {
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
		public void deleteAll(Iterable<? extends UserEntity> entities) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void deleteAll() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void delete(UserEntity entity) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public long count() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public UserEntity getUser(String userName) {
			UserEntity user = new UserEntity();
			user.setPassword("bjhgjdhg767");
			user.setUserId(89);
			user.setUserName("testUser");
			if(userName.equals(user.getUserName()))
				return user;
			return null;
		}
	};
	
	@Test
	public void getUser() {		
		UserEntity dummyUser = userRepo.getUser("testUser");
		assertNotNull(dummyUser);
	}
}
