package com.example.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.connection.MySQLConnection;
import com.example.dataModels.UserEntity;

public class UserDao {
	private Logger logger = LoggerFactory.getLogger(Logger.class);
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public void signUp(UserEntity user) throws ClassNotFoundException, SQLException {
		Connection conn = MySQLConnection.getConnection();
		Statement stmt =  conn.createStatement();
		Integer id = user.getUserId();
		String name = user.getUserName();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		String pswrd = user.getPassword();
		System.out.println("Signup request received from" +name+" "+ pswrd);
		
		String query = "Insert into user (userId, userName, password) values ('" + id +"','"+ name+"','"+ pswrd+"')";
		stmt.executeUpdate(query);
		logger.info(name +" " + " has signed up successfully!");
		conn.close();
	}

	public void deleteUser(Integer userId) throws ClassNotFoundException, SQLException {
		Connection conn = MySQLConnection.getConnection();
		Statement stmt = conn.createStatement();
		String query = "delete from user where userId='"+userId+"'";
		stmt.executeUpdate(query);
		conn.close();
	}
	
	public UserEntity getUser(String userName) throws ClassNotFoundException, SQLException {
		Connection conn = MySQLConnection.getConnection();
		Statement stmt = conn.createStatement();
		String query = "select * from user where username='"+userName+"'";
		ResultSet rs = stmt.executeQuery(query);
		String password = "";
		Integer id = 0;
		while(rs.next()) {
			password = rs.getString("password");
			id = rs.getInt("userId");
		}
		rs.close();
		UserEntity user = new UserEntity();
		user.setPassword(password);
		user.setUserName(userName);
		user.setUserId(id);
		logger.info("User details: "+userName+" "+password+" "+id);
		return user;
	}
}
