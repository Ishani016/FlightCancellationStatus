package com.flightInventory.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.flightInventory.dataModels.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
	
	@Query(value="select * from user where user_name= :userName", nativeQuery=true)
	public UserEntity getUser(@Param("userName") String userName);
	/*
	public void signUp(UserEntity user) throws ClassNotFoundException, SQLException {
		Connection conn = MySQLConnection.getConnection();
		Statement stmt =  conn.createStatement();
		Integer id = user.getUserId();
		String name = user.getUserName();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		String pswrd = user.getPassword();
		System.out.println("Signup request received from " +name+" "+ pswrd+" "+id);
		
		String query = "Insert into user (user_id, user_name, password) values ('" + id +"','"+ name+"','"+ pswrd+"')";
		stmt.executeUpdate(query);
		logger.info(name +" " + " has signed up successfully!");
		conn.close();
	}

	public void deleteUser(Integer userId) throws ClassNotFoundException, SQLException {
		Connection conn = MySQLConnection.getConnection();
		Statement stmt = conn.createStatement();
		String query = "delete from user where user_id='"+userId+"'";
		stmt.executeUpdate(query);
		conn.close();
	}
	
	public UserEntity getUser(String userName) throws ClassNotFoundException, SQLException {
		Connection conn = MySQLConnection.getConnection();
		Statement stmt = conn.createStatement();
		String query = "select * from user where user_name='"+userName+"'";
		ResultSet rs = stmt.executeQuery(query);
		String password = "";
		Integer id = 0;
		while(rs.next()) {
			password = rs.getString("password");
			id = rs.getInt("user_id");
		}
		rs.close();
		UserEntity user = new UserEntity();
		user.setPassword(password);
		user.setUserName(userName);
		user.setUserId(id);
		logger.info("User details requested by: "+userName);
		return user;
	}*/
}
