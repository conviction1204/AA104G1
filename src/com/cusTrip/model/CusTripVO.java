package com.cusTrip.model;
import java.sql.Date;
import java.sql.Timestamp;

public class CusTripVO {
	private String cusTripId;
	private String cusId;
	private String tripName;
	private Date dateSt;
	private String shareYn;
	private Timestamp shareDate;
	
	public String getShareYn() {
		return shareYn;
	}
	public void setShareYn(String shareYn) {
		this.shareYn = shareYn;
	}
	public Timestamp getShareDate() {
		return shareDate;
	}
	public void setShareDate(Timestamp shareDate) {
		this.shareDate = shareDate;
	}
	public String getCusTripId() {
		return cusTripId;
	}
	public void setCusTripId(String cusTripId) {
		this.cusTripId = cusTripId;
	}
	public String getCusId() {
		return cusId;
	}
	public void setCusId(String cusId) {
		this.cusId = cusId;
	}
	public String getTripName() {
		return tripName;
	}
	public void setTripName(String tripName) {
		this.tripName = tripName;
	}
	public Date getDateSt() {
		return dateSt;
	}
	public void setDateSt(Date dateSt) {
		this.dateSt = dateSt;
	}
	
	
}
