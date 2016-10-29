package com.cusTravelPic.model;
import java.sql.Date;

public class CusTravelPicVO implements java.io.Serializable{
	private String cusTravelPicId;
	private String cusTravelDetailId;
	private String picName;
	private String picFiletype;
	private Date dateRecord;	
	private byte[] pic;
	
	public String getCusTravelPicId() {
		return cusTravelPicId;
	}
	public void setCusTravelPicId(String cusTravelPicId) {
		this.cusTravelPicId = cusTravelPicId;
	}
	public String getCusTravelDetailId() {
		return cusTravelDetailId;
	}
	public void setCusTravelDetailId(String cusTravelDetailId) {
		this.cusTravelDetailId = cusTravelDetailId;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getPicFiletype() {
		return picFiletype;
	}
	public void setPicFiletype(String picFiletype) {
		this.picFiletype = picFiletype;
	}
	public Date getDateRecord() {
		return dateRecord;
	}
	public void setDateRecord(Date dateRecord) {
		this.dateRecord = dateRecord;
	}
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	
	
}
