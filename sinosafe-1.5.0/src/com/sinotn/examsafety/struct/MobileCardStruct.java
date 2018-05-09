package com.sinotn.examsafety.struct;

import struct.CString;
import struct.StructClass;
import struct.StructField;

@StructClass
public class MobileCardStruct extends BaseStruct{

	/*
	 * 上报数据类型
	 */
	@StructField(order = 0)
	public int dataType ;
	
	/*
	 * 数据采集时间
	 */
	@StructField(order = 1)
	public CString datetime = new CString(16);
	
	/*
	 * 设备串号
	 */
	@StructField(order = 2)
	public CString imei = new CString(16);
	
	/*
	 * 设备剩余电量
	 */
	@StructField(order = 3)
	public int dumpEnergy;
	/*
	 * 设备信号
	 */
	@StructField(order = 4)
	public int signalStrength;
	
	/*
	 * 考点
	 */
	@StructField(order = 5)
	public int examPlace;
	
	/*
	 * 科目
	 */
	@StructField(order = 6)
	public int examSubject;
	/*
	 * 考场
	 */
	@StructField(order = 7)
	public int examRoom;
	/*
	 * 题卡数量
	 */
	@StructField(order = 8)
	public int examCardAmount;
	/*
	 * 题卡
	 */
	@StructField(order = 9)
	public int[] examCard = new int[0];
	
	
	public MobileCardStruct(){
		
	}
	public MobileCardStruct(int examCardAmount){
		examCard = new int[examCardAmount];
	}
	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public String getDatetime() {
		return this.getString(datetime);
	}

	public void setDatetime(String datetime) {
		this.datetime = new CString(datetime,this.datetime.length());
	}

	public String getImei() {
		return this.getString(imei);
	}

	public void setImei(String imei) {
		this.imei = new CString(imei,this.imei.length());
	}

	public int getDumpEnergy() {
		return dumpEnergy;
	}

	public void setDumpEnergy(int dumpEnergy) {
		this.dumpEnergy = dumpEnergy;
	}

	public int getSignalStrength() {
		return signalStrength;
	}

	public void setSignalStrength(int signalStrength) {
		this.signalStrength = signalStrength;
	}

	public int getExamPlace() {
		return examPlace;
	}

	public void setExamPlace(int examPlace) {
		this.examPlace = examPlace;
	}

	public int getExamSubject() {
		return examSubject;
	}

	public void setExamSubject(int examSubject) {
		this.examSubject = examSubject;
	}

	public int getExamRoom() {
		return examRoom;
	}

	public void setExamRoom(int examRoom) {
		this.examRoom = examRoom;
	}

	public int[] getExamCard() {
		return examCard;
	}

	public void setExamCard(int[] examCard) {
		this.examCard = examCard;
	}

	public int getExamCardAmount() {
		return examCardAmount;
	}

	public void setExamCardAmount(int examCardAmount) {
		this.examCardAmount = examCardAmount;
	}
	
}
