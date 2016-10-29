package com.cusTripCoeditMsg;
import java.sql.Timestamp;

public class CusTripCoeditMsgVO {
	private String cusTripCoeditMsgId;
	private String cusTripId;
	private String cusId;
	private String content;
	private Timestamp dateRecord;
	
	public String getCusTripCoeditMsgId() {
		return cusTripCoeditMsgId;
	}
	public void setCusTripCoeditMsgId(String cusTripCoeditMsgId) {
		this.cusTripCoeditMsgId = cusTripCoeditMsgId;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getDateRecord() {
		return dateRecord;
	}
	public void setDateRecord(Timestamp dateRecord) {
		this.dateRecord = dateRecord;
	}
}
