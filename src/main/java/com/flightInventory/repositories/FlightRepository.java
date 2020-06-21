package com.flightInventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flightInventory.dataModels.Flight;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Integer> {
	
	@Query(value="select * from flight where source= :source and destination= :destination and curr_capacity>=0", nativeQuery=true)
	public List<Flight> getAvailableFlights(@Param("source") String source, @Param("destination") String destination);

	@Query(value="select f_id from flight where flight_name= :flightName", nativeQuery = true)
	public Integer findFIdByFlightName(@Param("flightName") String flightName);

}

