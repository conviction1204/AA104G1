package com.cusFavoriteTrip.model;
import java.sql.Date;

public class CusFavoriteTripVO implements java.io.Serializable{
	private String cusId;
	private String cusTripId;
	private String tripName;
	
	public String getCusId() {
		return cusId;
	}
	public void setCusId(String cusId) {
		this.cusId = cusId;
	}
	public String getCusTripId() {
		return cusTripId;
	}
	public void setCusTripId(String cusTripId) {
		this.cusTripId = cusTripId;
	}
	public String getTripName() {
		return tripName;
	}
	public void setTripName(String tripName) {
		this.tripName = tripName;
	}
	
}
