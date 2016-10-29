package com.cusTravelNote.model;
import java.sql.Date;

public class CusTravelNoteVO implements java.io.Serializable{
	private String cusTravelNoteId;
	private String cusId;
	private String cusTripId;
	private Date dateRecord;
	private String noteName;
	
	public String getCusTravelNoteId() {
		return cusTravelNoteId;
	}
	public void setCusTravelNoteId(String cusTravelNoteId) {
		this.cusTravelNoteId = cusTravelNoteId;
	}
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
	public Date getDateRecord() {
		return dateRecord;
	}
	public void setDateRecord(Date dateRecord) {
		this.dateRecord = dateRecord;
	}
	public String getNoteName() {
		return noteName;
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	
}
