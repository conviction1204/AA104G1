package com.cusTripDay.model;
import java.sql.Timestamp;

public class CusTripDayVO {
	private String cusTripDayId;
	private String cusTripId;
	private Integer dayNumber;
	private Timestamp dateSt;
	
	public String getCusTripDayId() {
		return cusTripDayId;
	}
	public void setCusTripDayId(String cusTripDayId) {
		this.cusTripDayId = cusTripDayId;
	}
	public String getCusTripId() {
		return cusTripId;
	}
	public void setCusTripId(String cusTripId) {
		this.cusTripId = cusTripId;
	}
	public Integer getDayNumber() {
		return dayNumber;
	}
	public void setDayNumber(Integer dayNumber) {
		this.dayNumber = dayNumber;
	}
	public Timestamp getDateSt() {
		return dateSt;
	}
	public void setDateSt(Timestamp dateSt) {
		this.dateSt = dateSt;
	}
}
