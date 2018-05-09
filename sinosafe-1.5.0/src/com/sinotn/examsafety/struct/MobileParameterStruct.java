package com.sinotn.examsafety.struct;

import struct.CString;
import struct.StructClass;
import struct.StructField;

@StructClass
public class MobileParameterStruct extends BaseStruct  {
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
}
