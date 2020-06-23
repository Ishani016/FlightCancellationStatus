package com.flightInventory.api.dataModels;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user_flight")
public class UserFlight {

	@Id
	Integer flightId;
	
	String cancellationStatus;
	String bookingStatus;
	Integer fId;

	@JoinColumn(name="user_id")
	Integer userId;

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public void setCancellationStatus(String cancellationStatus) {
		this.cancellationStatus = cancellationStatus;
	}

	public void setFId(Integer fId) {
		this.fId = fId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public Integer getFId() {
		return fId;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getCancellationStatus() {
		return cancellationStatus;
	}
	
}
