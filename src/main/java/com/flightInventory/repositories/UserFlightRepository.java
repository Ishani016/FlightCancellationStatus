package com.flightInventory.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.flightInventory.dataModels.UserFlight;

public interface UserFlightRepository extends CrudRepository<UserFlight, Integer> {

	@Query(value="select * from user_flight where f_id= :fId and user_id= :userId", nativeQuery=true)
	public UserFlight findUserByFlightId(@Param("fId") Integer fId, @Param("userId") Integer userId);
	
	@Query(value="select cancellation_status from user_flight where f_id= :fId and user_id= :userId", nativeQuery = true)
	public String findCancelFlightStatus(@Param("fId") Integer fId, @Param("userId") Integer userId);
}
