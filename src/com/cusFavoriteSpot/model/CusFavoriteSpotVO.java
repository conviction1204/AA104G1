package com.cusFavoriteSpot.model;
import java.sql.Date;

public class CusFavoriteSpotVO implements java.io.Serializable{
	private String cusId;
	private String sysSpotId;
	private String spotName;
	
	public String getCusId() {
		return cusId;
	}
	public void setCusId(String cusId) {
		this.cusId = cusId;
	}
	public String getSysSpotId() {
		return sysSpotId;
	}
	public void setSysSpotId(String sysSpotId) {
		this.sysSpotId = sysSpotId;
	}
	public String getSpotName() {
		return spotName;
	}
	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}
	

}
