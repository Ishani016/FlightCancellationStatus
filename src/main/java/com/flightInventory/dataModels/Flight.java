package com.flightInventory.dataModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="flight")
public class Flight {
	@Id
	Integer fId;
	String source;
	String destination;
	
	String flightName;
	Integer cost;
	Integer currCapacity;
	Integer maxCapacity;
	
	public String getSource() {
		return source;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public String getFlightName() {
		return flightName;
	}
	
	public Integer getCost() {
		return cost;
	}
	
	public Integer getCurrCapacity() {
		return currCapacity;
	}
	
	public Integer getFId() {
		return fId;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	
	public void setFId(Integer fId) {
		this.fId = fId;
	}
	
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	
	public void setCurrCapacity(Integer currCapacity) {
		this.currCapacity = currCapacity;
	}
	
	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public Integer getMaxCapacity() {
		return maxCapacity;
	}
	
}
