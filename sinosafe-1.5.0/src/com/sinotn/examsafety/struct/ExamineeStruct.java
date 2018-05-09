package com.sinotn.examsafety.struct;

import struct.CString;
import struct.StructClass;
import struct.StructField;

@StructClass
public class ExamineeStruct  extends BaseStruct{
	
	@StructField(order = 0)
	public CString licence = new CString(16);
	
	@StructField(order = 1)
	public CString identity = new CString(20);
	
	@StructField(order = 2)
	public CString examineeName = new CString(60);
	
	@StructField(order = 3)
	public long examRoom;
	
	@StructField(order = 4)
	public int seatNumber;

	public String getLicence() {
		return this.getString(licence);
	}

	public void setLicence(String licence) {
		this.licence = new CString(licence,this.licence.length());
	}

	public String getIdentity() {
		return this.getString(identity);
	}

	public void setIdentity(String identity) {
		this.identity = new CString(identity,this.identity.length());
	}

	public String getExamineeName() {
		return this.getString(examineeName);
	}

	public void setExamineeName(String examineeName) {
		this.examineeName = new CString(examineeName,this.examineeName.length());
	}

	public long getExamRoom() {
		return examRoom;
	}

	public void setExamRoom(long examRoom) {
		this.examRoom = examRoom;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

}
