package com.sinotn.examsafety.struct;

import struct.StructClass;
import struct.StructField;
@StructClass
public class RoomStruct extends BaseStruct{
	@StructField(order = 0)
	public short roomNo ;
	
	
	@StructField(order = 1)
	public byte examineeSum;


	public short getRoomNo() {
		return roomNo;
	}


	public void setRoomNo(short roomNo) {
		this.roomNo = roomNo;
	}


	public byte getExamineeSum() {
		return examineeSum;
	}


	public void setExamineeSum(byte examineeSum) {
		this.examineeSum = examineeSum;
	}
	
	

}
