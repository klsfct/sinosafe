package com.sinotn.examsafety.vo;

public class DeviceInfoVo {

	String deviceSerial; 	//设备序列号
	String deviceName;  	//设备名称
	String deviceType; 	 	//设备类型
	String status; 	 		//在线状态：0-不在线，1-在线
	String isEncrypt; 	 	//是否加密：0-不加密，1-加密
	String defence; 	 	//具有防护能力的设备布撤防状态：0-睡眠，8-在家，16-外出，普通IPC布撤防状态：0-撤防，1-布防
	String deviceVersion; 	//设备版本号
	public String getDeviceSerial() {
		return deviceSerial;
	}
	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsEncrypt() {
		return isEncrypt;
	}
	public void setIsEncrypt(String isEncrypt) {
		this.isEncrypt = isEncrypt;
	}
	public String getDefence() {
		return defence;
	}
	public void setDefence(String defence) {
		this.defence = defence;
	}
	public String getDeviceVersion() {
		return deviceVersion;
	}
	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}
	
	
}
