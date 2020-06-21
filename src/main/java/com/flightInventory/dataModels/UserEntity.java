package com.flightInventory.dataModels;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Entity(name="user")
public class UserEntity {
	@Id
	Integer userId;
	String userName;
	String password;
	
	public UserEntity() {	
	}
	
	public UserEntity(int id, String userName, String password) {
		this.userId = id;
		this.userName = userName;
		this.password = password;
	}


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

