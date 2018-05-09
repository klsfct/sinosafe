package com.sinotn.examsafety.struct;

import struct.CString;
import struct.StructClass;
import struct.StructField;

@StructClass
public class FaceExamineeStruct extends BaseStruct {

	/*
	 * 上报数据类型
	 */
	@StructField(order = 0)
	public int dataType;

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
	public int photoPrecision;
	/*
	 * 上传姓名
	 */
	@StructField(order = 7)
	public CString faceExamineeName = new CString(64);
	/*
	 * 上传的身份证有效期
	 */
	@StructField(order = 8)
	public CString faceIndetityDate = new CString(32);
	/*
	 * 上传的准考证
	 */
	@StructField(order = 9)
	public CString licence = new CString(16);
	/*
	 * 人像比分
	 */
	@StructField(order = 10)
	public int photoResult;
	/*
	 * 系统是否通过
	 */
	@StructField(order = 11)
	public int sysPass;
	/*
	 * 人为是否通过
	 */
	@StructField(order = 12)
	public int specialPass;
	/*
	 * 照片大小(
	 */
	@StructField(order = 13)
	public int fileSize;
	/*
	 * 拍照照片数据
	 */
	@StructField(order = 14)
	public byte[] buffer = new byte[0];

	public FaceExamineeStruct(){
	}
	public FaceExamineeStruct(int fileSize){
		super();
		int fileBufferLength = fileSize - 184;
		this.buffer = new byte[fileBufferLength];
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
		this.datetime = new CString(datetime, this.datetime.length());
	}

	public String getImei() {
		return this.getString(imei);
	}

	public void setImei(String imei) {
		this.imei = new CString(imei, this.imei.length());
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

	public int getPhotoPrecision() {
		return photoPrecision;
	}

	public void setPhotoPrecision(int photoPrecision) {
		this.photoPrecision = photoPrecision;
	}
	public String getFaceExamineeName() {
		return this.getString(faceExamineeName);
	}
	public void setFaceExamineeName(String faceExamineeName) {
		this.faceExamineeName = new CString(faceExamineeName,this.faceExamineeName.length());
	}
	public String getFaceIndetityDate() {
		return this.getString(faceIndetityDate);
	}
	public void setFaceIndetityDate(String faceIndetityDate) {
		this.faceIndetityDate = new CString(faceIndetityDate,faceIndetityDate.length());
	}
	public String getLicence() {
		return this.getString(licence);
	}
	public void setLicence(String licence) {
		this.licence = new CString(licence,licence.length());
	}
	public int getPhotoResult() {
		return photoResult;
	}
	public void setPhotoResult(int photoResult) {
		this.photoResult = photoResult;
	}
	public int getSysPass() {
		return sysPass;
	}
	public void setSysPass(int sysPass) {
		this.sysPass = sysPass;
	}
	public int getSpecialPass() {
		return specialPass;
	}
	public void setSpecialPass(int specialPass) {
		this.specialPass = specialPass;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public byte[] getBuffer() {
		return buffer;
	}
	public void setBuffer(byte[] buffer) {
		this.buffer = buffer;
	}
}
