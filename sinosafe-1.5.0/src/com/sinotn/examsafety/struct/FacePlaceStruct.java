package com.sinotn.examsafety.struct;

import struct.CString;
import struct.StructClass;
import struct.StructField;

@StructClass
public class FacePlaceStruct extends BaseStruct{

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
	public CString imei = new CString(20);
	
	/*
	 * 考点
	 */
	@StructField(order = 3)
	public int examPlace;
	/*
	 * 通道类型
	 */
	@StructField(order = 4)
	public int passType;
	
	/*
	 * 科目
	 */
	@StructField(order = 5)
	public int examSubject;
	/*
	 * 人脸识别精度
	 */
	@StructField(order = 6)
	public int verifyPrecision;
	
	
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

	public int getExamPlace() {
		return examPlace;
	}

	public void setExamPlace(int examPlace) {
		this.examPlace = examPlace;
	}

	public int getPassType() {
		return passType;
	}

	public void setPassType(int passType) {
		this.passType = passType;
	}

	public int getExamSubject() {
		return examSubject;
	}

	public void setExamSubject(int examSubject) {
		this.examSubject = examSubject;
	}

	public int getVerifyPrecision() {
		return verifyPrecision;
	}

	public void setVerifyPrecision(int verifyPrecision) {
		this.verifyPrecision = verifyPrecision;
	}

	
}
