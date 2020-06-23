package com.flightInventory.api.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flightInventory.api.dataModels.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
	
	@Query(value="select * from user where user_name= :userName", nativeQuery=true)
	public UserEntity getUser(@Param("userName") String userName);

}
