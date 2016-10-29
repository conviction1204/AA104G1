package com.cusTravelDetail.model;
import java.sql.Date;

public class CusTravelDetailVO implements java.io.Serializable{
	private String cusTravelDetailId;
	private String cusTravelNoteId;
	private Date dateRecord;	
	private String content;	
	private String detailName;
	
	public String getCusTravelDetailId() {
		return cusTravelDetailId;
	}
	public void setCusTravelDetailId(String cusTravelDetailId) {
		this.cusTravelDetailId = cusTravelDetailId;
	}
	public String getCusTravelNoteId() {
		return cusTravelNoteId;
	}
	public void setCusTravelNoteId(String cusTravelNoteId) {
		this.cusTravelNoteId = cusTravelNoteId;
	}
	public Date getDateRecord() {
		return dateRecord;
	}
	public void setDateRecord(Date dateRecord) {
		this.dateRecord = dateRecord;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDetailName() {
		return detailName;
	}
	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}
	
	
}
