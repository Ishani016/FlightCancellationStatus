package com.example.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import com.example.connection.MySQLConnection;
import com.example.dataModels.UserEntity;


public class CancelDao {
	private Logger logger = LoggerFactory.getLogger(Logger.class);
    
	public void cancelFlight(Integer flightId, Integer userId) throws SQLException, ClassNotFoundException {
		Connection conn = MySQLConnection.getConnection();
		Statement stmt = conn.createStatement();
		String query = "update flight set cancellationStatus='Cancelled' where userId=userId and flightId=flightId";
		stmt.executeUpdate(query);
		logger.info("Flight for "+flightId+" for user "+ userId+ " has been cancelled.");
		conn.close();
	}
	
	@Cacheable("cancellationStatus")
	public String cancelFlightStatus(Integer flightId, Integer userId) throws ClassNotFoundException, SQLException {
		Connection conn = MySQLConnection.getConnection();
		String query = "select cancellationStatus from flight where flightId=flightId and userId=userId";
		String status = "";
		Statement stmt = conn.createStatement();
		stmt.executeQuery(query);
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()) {
			status = rs.getString("cancellationStatus");
		}
		rs.close();
		conn.close();
		return status;
	}
	
	@Cacheable("user")
	public UserEntity getUserById(Integer userId) throws ClassNotFoundException, SQLException {
		Connection conn = MySQLConnection.getConnection();
		Statement stmt = conn.createStatement();
		String query = "select * from user where userId='"+userId+"'";
		ResultSet rs = stmt.executeQuery(query);
		UserEntity user = new UserEntity();
		while(rs.next()) {
			user.setPassword(rs.getString("password"));
			user.setUserId(rs.getInt("userId"));
			user.setUserName(rs.getString("userName"));
		}
		rs.close();
		conn.close();
		return user;

	}
}
