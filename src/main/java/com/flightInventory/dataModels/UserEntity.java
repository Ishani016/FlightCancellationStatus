package com.flightInventory.dataModels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user")
public class UserEntity {
	@Id
	Integer userId;
	String userName;
	String password;
	
	
	public Integer getUserId() {
		return this.userId;
	}


	public String getUserName() {
		return this.userName;
	}


	public String getPassword() {
		return this.password;
	}	
	
	public void setUserId(Integer id) {
		this.userId = id;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}
}

