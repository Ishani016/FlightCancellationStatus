package com.flightInventory.api.dataModels;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity(name="user")
public class UserEntity {
	@Id
	Integer userId;
	String userName;
	String password;
	String docUrl;
	
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
	public String getDocUrl() {
		return docUrl;
	}
	
	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}
}

